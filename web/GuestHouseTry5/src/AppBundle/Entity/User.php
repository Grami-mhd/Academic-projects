<?php

namespace AppBundle\Entity;
use FOS\UserBundle\Model\User as BaseUser;
use Doctrine\ORM\Mapping as ORM;

/**
 * User
 *
 * @ORM\Table(name="user", uniqueConstraints={@ORM\UniqueConstraint(name="UNIQ_8D93D64992FC23A8", columns={"username_canonical"}), @ORM\UniqueConstraint(name="UNIQ_8D93D649A0D96FBF", columns={"email_canonical"}), @ORM\UniqueConstraint(name="UNIQ_8D93D649C05FB297", columns={"confirmation_token"})})
 * @ORM\Entity
 */
class User extends BaseUser
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    protected $id;

    /**
     * @var string
     *
     * @ORM\Column(name="firstname", type="string", length=30, nullable=false)
     */
    private $firstname;

    /**
     * @var string
     *
     * @ORM\Column(name="lastname", type="string", length=30, nullable=false)
     */
    private $lastname;

    /**
     * @var string
     *
     * @ORM\Column(name="country", type="string", length=30, nullable=true)
     */
    private $country;

    /**
     * @var string
     *
     * @ORM\Column(name="town", type="string", length=30, nullable=true)
     */
    private $town;

    /**
     * @var integer
     *
     * @ORM\Column(name="phone", type="bigint", nullable=true)
     */
    private $phone;

    /**
     * @var integer
     *
     * @ORM\Column(name="points", type="integer", nullable=true)
     */
    private $points = '0';

    /**
     * @var boolean
     *
     * @ORM\Column(name="type", type="boolean", nullable=true)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="picture", type="blob", nullable=true)
     */
    private $picture;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="Forum", inversedBy="fkBanneduser")
     * @ORM\JoinTable(name="ban",
     *   joinColumns={
     *     @ORM\JoinColumn(name="fk_banneduser", referencedColumnName="id")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="fk_forum", referencedColumnName="id")
     *   }
     * )
     */
    private $fkForum;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="Comment", inversedBy="fkCommentReportOwnerid")
     * @ORM\JoinTable(name="commentreport",
     *   joinColumns={
     *     @ORM\JoinColumn(name="fk_comment_report_ownerid", referencedColumnName="id")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="fk_commentid", referencedColumnName="id")
     *   }
     * )
     */
    private $fkCommentid;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="Forum", mappedBy="fkForumReportUserid")
     */
    private $fkForumReportForumid;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="House", mappedBy="fkStayUserid")
     */
    private $fkStayHouseid;

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->fkForum = new \Doctrine\Common\Collections\ArrayCollection();
        $this->fkCommentid = new \Doctrine\Common\Collections\ArrayCollection();
        $this->fkForumReportForumid = new \Doctrine\Common\Collections\ArrayCollection();
        $this->fkStayHouseid = new \Doctrine\Common\Collections\ArrayCollection();
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
    public function getFirstname()
    {
        return $this->firstname;
    }

    /**
     * @param string $firstname
     */
    public function setFirstname($firstname)
    {
        $this->firstname = $firstname;
    }

    /**
     * @return string
     */
    public function getLastname()
    {
        return $this->lastname;
    }

    /**
     * @param string $lastname
     */
    public function setLastname($lastname)
    {
        $this->lastname = $lastname;
    }

    /**
     * @return string
     */
    public function getCountry()
    {
        return $this->country;
    }

    /**
     * @param string $country
     */
    public function setCountry($country)
    {
        $this->country = $country;
    }

    /**
     * @return string
     */
    public function getTown()
    {
        return $this->town;
    }

    /**
     * @param string $town
     */
    public function setTown($town)
    {
        $this->town = $town;
    }

    /**
     * @return int
     */
    public function getPhone()
    {
        return $this->phone;
    }

    /**
     * @param int $phone
     */
    public function setPhone($phone)
    {
        $this->phone = $phone;
    }

    /**
     * @return int
     */
    public function getPoints()
    {
        return $this->points;
    }

    /**
     * @param int $points
     */
    public function setPoints($points)
    {
        $this->points = $points;
    }

    /**
     * @return boolean
     */
    public function isType()
    {
        return $this->type;
    }

    /**
     * @param boolean $type
     */
    public function setType($type)
    {
        $this->type = $type;
    }

    /**
     * @return string
     */
    public function getPicture()
    {
        return $this->picture;
    }

    /**
     * @param string $picture
     */
    public function setPicture($picture)
    {
        $this->picture = $picture;
    }

    /**
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getFkForum()
    {
        return $this->fkForum;
    }

    /**
     * @param \Doctrine\Common\Collections\Collection $fkForum
     */
    public function setFkForum($fkForum)
    {
        $this->fkForum = $fkForum;
    }

    /**
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getFkCommentid()
    {
        return $this->fkCommentid;
    }

    /**
     * @param \Doctrine\Common\Collections\Collection $fkCommentid
     */
    public function setFkCommentid($fkCommentid)
    {
        $this->fkCommentid = $fkCommentid;
    }

    /**
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getFkForumReportForumid()
    {
        return $this->fkForumReportForumid;
    }

    /**
     * @param \Doctrine\Common\Collections\Collection $fkForumReportForumid
     */
    public function setFkForumReportForumid($fkForumReportForumid)
    {
        $this->fkForumReportForumid = $fkForumReportForumid;
    }

    /**
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getFkStayHouseid()
    {
        return $this->fkStayHouseid;
    }

    /**
     * @param \Doctrine\Common\Collections\Collection $fkStayHouseid
     */
    public function setFkStayHouseid($fkStayHouseid)
    {
        $this->fkStayHouseid = $fkStayHouseid;
    }



}

