<?php

namespace AdminBundle\Entity;

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
    public function getSubject()
    {
        return $this->subject;
    }

    /**
     * @param string $subject
     */
    public function setSubject($subject)
    {
        $this->subject = $subject;
    }

    /**
     * @return boolean
     */
    public function isIscreated()
    {
        return $this->iscreated;
    }

    /**
     * @param boolean $iscreated
     */
    public function setIscreated($iscreated)
    {
        $this->iscreated = $iscreated;
    }

    /**
     * @return \User
     */
    public function getFkForumOwnerid()
    {
        return $this->fkForumOwnerid;
    }

    /**
     * @param \User $fkForumOwnerid
     */
    public function setFkForumOwnerid($fkForumOwnerid)
    {
        $this->fkForumOwnerid = $fkForumOwnerid;
    }

    /**
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getFkBanneduser()
    {
        return $this->fkBanneduser;
    }

    /**
     * @param \Doctrine\Common\Collections\Collection $fkBanneduser
     */
    public function setFkBanneduser($fkBanneduser)
    {
        $this->fkBanneduser = $fkBanneduser;
    }

    /**
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getFkForumReportUserid()
    {
        return $this->fkForumReportUserid;
    }

    /**
     * @param \Doctrine\Common\Collections\Collection $fkForumReportUserid
     */
    public function setFkForumReportUserid($fkForumReportUserid)
    {
        $this->fkForumReportUserid = $fkForumReportUserid;
    }

}

