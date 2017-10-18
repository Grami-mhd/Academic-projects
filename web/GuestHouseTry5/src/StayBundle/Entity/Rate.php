<?php

namespace StayBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Rate
 *
 * @ORM\Table(name="rate", indexes={@ORM\Index(name="fk_houseid", columns={"fk_rate_houseid", "fk_rate_ownerid"}), @ORM\Index(name="fk_ownerid", columns={"fk_rate_ownerid"}), @ORM\Index(name="IDX_DFEC3F39DFCC3122", columns={"fk_rate_houseid"})})
 * @ORM\Entity
 */
class Rate
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
     * @var integer
     *
     * @ORM\Column(name="rate", type="integer", nullable=false)
     */
    private $rate;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_rate_ownerid", referencedColumnName="id")
     * })
     */
    private $fkRateOwnerid;

    /**
     * @var \House
     *
     * @ORM\ManyToOne(targetEntity="House")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_rate_houseid", referencedColumnName="id")
     * })
     */
    private $fkRateHouseid;

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
     * @return int
     */
    public function getRate()
    {
        return $this->rate;
    }

    /**
     * @param int $rate
     */
    public function setRate($rate)
    {
        $this->rate = $rate;
    }

    /**
     * @return \User
     */
    public function getFkRateOwnerid()
    {
        return $this->fkRateOwnerid;
    }

    /**
     * @param \User $fkRateOwnerid
     */
    public function setFkRateOwnerid($fkRateOwnerid)
    {
        $this->fkRateOwnerid = $fkRateOwnerid;
    }

    /**
     * @return \House
     */
    public function getFkRateHouseid()
    {
        return $this->fkRateHouseid;
    }

    /**
     * @param \House $fkRateHouseid
     */
    public function setFkRateHouseid($fkRateHouseid)
    {
        $this->fkRateHouseid = $fkRateHouseid;
    }


}

