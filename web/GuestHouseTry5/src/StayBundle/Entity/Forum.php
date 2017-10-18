<?php

namespace StayBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Forum
 *
 * @ORM\Table(name="forum", indexes={@ORM\Index(name="fk_ownerid", columns={"fk_forum_ownerid"})})
 * @ORM\Entity
 */
class Forum
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
     * @ORM\Column(name="subject", type="text", length=65535, nullable=false)
     */
    private $subject;

    /**
     * @var boolean
     *
     * @ORM\Column(name="iscreated", type="boolean", nullable=true)
     */
    private $iscreated = '0';

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_forum_ownerid", referencedColumnName="id")
     * })
     */
    private $fkForumOwnerid;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="User", mappedBy="fkForum")
     */
    private $fkBanneduser;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="User", inversedBy="fkForumReportForumid")
     * @ORM\JoinTable(name="forumreport",
     *   joinColumns={
     *     @ORM\JoinColumn(name="fk_forum_report_forumid", referencedColumnName="id")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="fk_forum_report_userid", referencedColumnName="id")
     *   }
     * )
     */
    private $fkForumReportUserid;

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->fkBanneduser = new \Doctrine\Common\Collections\ArrayCollection();
        $this->fkForumReportUserid = new \Doctrine\Common\Collections\ArrayCollection();
    }

}

