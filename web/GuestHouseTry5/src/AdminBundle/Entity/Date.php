<?php

namespace AdminBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Date
 *
 * @ORM\Table(name="date", indexes={@ORM\Index(name="fk_stayid", columns={"fk_addid"})})
 * @ORM\Entity
 */
class Date
{
    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     */
    private $date;

    /**
     * @var \House
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="House")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_addid", referencedColumnName="id")
     * })
     */
    private $fkAddid;

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
     * @return \House
     */
    public function getFkAddid()
    {
        return $this->fkAddid;
    }

    /**
     * @param \House $fkAddid
     */
    public function setFkAddid($fkAddid)
    {
        $this->fkAddid = $fkAddid;
    }


}

