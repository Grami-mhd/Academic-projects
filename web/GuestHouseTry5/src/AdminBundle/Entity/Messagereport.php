<?php

namespace AdminBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Messagereport
 *
 * @ORM\Table(name="messagereport", indexes={@ORM\Index(name="fk_reporter_id", columns={"fk_reporter_id"}), @ORM\Index(name="fk_reported_forum_id", columns={"fk_message_id"})})
 * @ORM\Entity
 */
class Messagereport
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
     * @var boolean
     *
     * @ORM\Column(name="istreated", type="boolean", nullable=false)
     */
    private $istreated = '0';

    /**
     * @var \Message
     *
     * @ORM\ManyToOne(targetEntity="Message")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_message_id", referencedColumnName="id")
     * })
     */
    private $fkMessage;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_reporter_id", referencedColumnName="id")
     * })
     */
    private $fkReporter;

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
     * @return boolean
     */
    public function isIstreated()
    {
        return $this->istreated;
    }

    /**
     * @param boolean $istreated
     */
    public function setIstreated($istreated)
    {
        $this->istreated = $istreated;
    }

    /**
     * @return \Message
     */
    public function getFkMessage()
    {
        return $this->fkMessage;
    }

    /**
     * @param \Message $fkMessage
     */
    public function setFkMessage($fkMessage)
    {
        $this->fkMessage = $fkMessage;
    }

    /**
     * @return \User
     */
    public function getFkReporter()
    {
        return $this->fkReporter;
    }

    /**
     * @param \User $fkReporter
     */
    public function setFkReporter($fkReporter)
    {
        $this->fkReporter = $fkReporter;
    }


}

