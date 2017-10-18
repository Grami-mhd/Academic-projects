<?php

namespace AdminBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Comment
 *
 * @ORM\Table(name="comment", indexes={@ORM\Index(name="fk_forumid", columns={"fk_comment_houseid"}), @ORM\Index(name="fk_ownerid", columns={"fk_comment_ownerid"})})
 * @ORM\Entity
 */
class Comment
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
     * @ORM\Column(name="comment", type="text", length=65535, nullable=true)
     */
    private $comment;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_comment_ownerid", referencedColumnName="id")
     * })
     */
    private $fkCommentOwnerid;

    /**
     * @var \House
     *
     * @ORM\ManyToOne(targetEntity="House")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_comment_houseid", referencedColumnName="id")
     * })
     */
    private $fkCommentHouseid;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="User", mappedBy="fkCommentid")
     */
    private $fkCommentReportOwnerid;

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->fkCommentReportOwnerid = new \Doctrine\Common\Collections\ArrayCollection();
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
    public function getComment()
    {
        return $this->comment;
    }

    /**
     * @param string $comment
     */
    public function setComment($comment)
    {
        $this->comment = $comment;
    }

    /**
     * @return \User
     */
    public function getFkCommentOwnerid()
    {
        return $this->fkCommentOwnerid;
    }

    /**
     * @param \User $fkCommentOwnerid
     */
    public function setFkCommentOwnerid($fkCommentOwnerid)
    {
        $this->fkCommentOwnerid = $fkCommentOwnerid;
    }

    /**
     * @return \House
     */
    public function getFkCommentHouseid()
    {
        return $this->fkCommentHouseid;
    }

    /**
     * @param \House $fkCommentHouseid
     */
    public function setFkCommentHouseid($fkCommentHouseid)
    {
        $this->fkCommentHouseid = $fkCommentHouseid;
    }

    /**
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getFkCommentReportOwnerid()
    {
        return $this->fkCommentReportOwnerid;
    }

    /**
     * @param \Doctrine\Common\Collections\Collection $fkCommentReportOwnerid
     */
    public function setFkCommentReportOwnerid($fkCommentReportOwnerid)
    {
        $this->fkCommentReportOwnerid = $fkCommentReportOwnerid;
    }

}

