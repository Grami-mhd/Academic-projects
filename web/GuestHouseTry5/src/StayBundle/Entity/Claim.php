<?php

namespace StayBundle\Entity;

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


}

