<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Claim
 *
 * @ORM\Table(name="claim", indexes={@ORM\Index(name="fk_stayid", columns={"fk_claim_houseid"}), @ORM\Index(name="fk_claimerid", columns={"fk_claim_ownerrid"})})
 * @ORM\Entity
 */
class Claim
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
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="text", type="text", length=65535, nullable=false)
     */
    private $text;

    /**
     * @var string
     *
     * @ORM\Column(name="status", type="text", length=65535, nullable=true)
     */
    private $status;

    /**
     * @var boolean
     *
     * @ORM\Column(name="istreated", type="boolean", nullable=false)
     */
    private $istreated = '0';

    /**
     * @var \House
     *
     * @ORM\ManyToOne(targetEntity="House")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_claim_houseid", referencedColumnName="id")
     * })
     */
    private $fkClaimHouseid;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_claim_ownerrid", referencedColumnName="id")
     * })
     */
    private $fkClaimOwnerrid;

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
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * @param \DateTime $date
     */
    public function setDate($date)
    {
        $this->date = $date;
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
     * @return string
     */
    public function getStatus()
    {
        return $this->status;
    }

    /**
     * @param string $status
     */
    public function setStatus($status)
    {
        $this->status = $status;
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
     * @return \House
     */
    public function getFkClaimHouseid()
    {
        return $this->fkClaimHouseid;
    }

    /**
     * @param \House $fkClaimHouseid
     */
    public function setFkClaimHouseid($fkClaimHouseid)
    {
        $this->fkClaimHouseid = $fkClaimHouseid;
    }

    /**
     * @return \User
     */
    public function getFkClaimOwnerrid()
    {
        return $this->fkClaimOwnerrid;
    }

    /**
     * @param \User $fkClaimOwnerrid
     */
    public function setFkClaimOwnerrid($fkClaimOwnerrid)
    {
        $this->fkClaimOwnerrid = $fkClaimOwnerrid;
    }




}

