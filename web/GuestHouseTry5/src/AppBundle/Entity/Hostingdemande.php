<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Hostingdemande
 *
 * @ORM\Table(name="hostingdemande", indexes={@ORM\Index(name="fk_senderid", columns={"fk_senderid"}), @ORM\Index(name="fk_receiverid", columns={"fk_receiverid"}), @ORM\Index(name="fk_houseid", columns={"fk_hosting_houseid"})})
 * @ORM\Entity
 */
class Hostingdemande
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
     *   @ORM\JoinColumn(name="fk_senderid", referencedColumnName="id")
     * })
     */
    private $fkSenderid;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_receiverid", referencedColumnName="id")
     * })
     */
    private $fkReceiverid;

    /**
     * @var \House
     *
     * @ORM\ManyToOne(targetEntity="House")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_hosting_houseid", referencedColumnName="id")
     * })
     */
    private $fkHostingHouseid;

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
    public function getFkSenderid()
    {
        return $this->fkSenderid;
    }

    /**
     * @param \User $fkSenderid
     */
    public function setFkSenderid($fkSenderid)
    {
        $this->fkSenderid = $fkSenderid;
    }

    /**
     * @return \User
     */
    public function getFkReceiverid()
    {
        return $this->fkReceiverid;
    }

    /**
     * @param \User $fkReceiverid
     */
    public function setFkReceiverid($fkReceiverid)
    {
        $this->fkReceiverid = $fkReceiverid;
    }

    /**
     * @return \House
     */
    public function getFkHostingHouseid()
    {
        return $this->fkHostingHouseid;
    }

    /**
     * @param \House $fkHostingHouseid
     */
    public function setFkHostingHouseid($fkHostingHouseid)
    {
        $this->fkHostingHouseid = $fkHostingHouseid;
    }



}

