<?php

namespace StayBundle\Entity;

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


}

