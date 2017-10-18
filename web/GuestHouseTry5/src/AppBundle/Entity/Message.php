<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Message
 *
 * @ORM\Table(name="message", indexes={@ORM\Index(name="fk_ownerid", columns={"fk_message_ownerid"}), @ORM\Index(name="fk_forum", columns={"fk_message_forumid"})})
 * @ORM\Entity
 */
class Message
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="text", type="text", length=65535, nullable=false)
     */
    private $text;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_message_ownerid", referencedColumnName="id")
     * })
     */
    private $fkMessageOwnerid;

    /**
     * @var \Forum
     *
     * @ORM\ManyToOne(targetEntity="Forum")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_message_forumid", referencedColumnName="id")
     * })
     */
    private $fkMessageForumid;

    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getText()
    {
        return $this->text;
    }

    /**
     * @param string $text
     */
    public function setText($text)
    {
        $this->text = $text;
    }

    /**
     * @return \User
     */
    public function getFkMessageOwnerid()
    {
        return $this->fkMessageOwnerid;
    }

    /**
     * @param \User $fkMessageOwnerid
     */
    public function setFkMessageOwnerid($fkMessageOwnerid)
    {
        $this->fkMessageOwnerid = $fkMessageOwnerid;
    }

    /**
     * @return \Forum
     */
    public function getFkMessageForumid()
    {
        return $this->fkMessageForumid;
    }

    /**
     * @param \Forum $fkMessageForumid
     */
    public function setFkMessageForumid($fkMessageForumid)
    {
        $this->fkMessageForumid = $fkMessageForumid;
    }



}

