<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * House
 *
 * @ORM\Table(name="house", uniqueConstraints={@ORM\UniqueConstraint(name="id", columns={"id"})}, indexes={@ORM\Index(name="fk_user", columns={"fk_user"})})
 * @ORM\Entity
 */
class House
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
     * @ORM\Column(name="country", type="string", length=30, nullable=false)
     */
    private $country;

    /**
     * @var string
     *
     * @ORM\Column(name="town", type="string", length=30, nullable=false)
     */
    private $town;

    /**
     * @var string
     *
     * @ORM\Column(name="nature", type="string", length=30, nullable=false)
     */
    private $nature;

    /**
     * @var integer
     *
     * @ORM\Column(name="price", type="integer", nullable=false)
     */
    private $price;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="text", length=65535, nullable=true)
     */
    private $description;

    /**
     * @var integer
     *
     * @ORM\Column(name="rating", type="integer", nullable=true)
     */
    private $rating;

    /**
     * @var boolean
     *
     * @ORM\Column(name="airconditioner", type="boolean", nullable=true)
     */
    private $airconditioner;

    /**
     * @var boolean
     *
     * @ORM\Column(name="fireplace", type="boolean", nullable=true)
     */
    private $fireplace;

    /**
     * @var boolean
     *
     * @ORM\Column(name="washingmachine", type="boolean", nullable=true)
     */
    private $washingmachine;

    /**
     * @var boolean
     *
     * @ORM\Column(name="dishwasher", type="boolean", nullable=true)
     */
    private $dishwasher;

    /**
     * @var boolean
     *
     * @ORM\Column(name="dvd", type="boolean", nullable=true)
     */
    private $dvd;

    /**
     * @var boolean
     *
     * @ORM\Column(name="indoorpool", type="boolean", nullable=true)
     */
    private $indoorpool;

    /**
     * @var boolean
     *
     * @ORM\Column(name="sauna", type="boolean", nullable=true)
     */
    private $sauna;

    /**
     * @var boolean
     *
     * @ORM\Column(name="jacuzzi", type="boolean", nullable=true)
     */
    private $jacuzzi;

    /**
     * @var boolean
     *
     * @ORM\Column(name="tv", type="boolean", nullable=true)
     */
    private $tv;

    /**
     * @var boolean
     *
     * @ORM\Column(name="elevator", type="boolean", nullable=true)
     */
    private $elevator;

    /**
     * @var boolean
     *
     * @ORM\Column(name="barbecue", type="boolean", nullable=true)
     */
    private $barbecue;

    /**
     * @var boolean
     *
     * @ORM\Column(name="garden", type="boolean", nullable=true)
     */
    private $garden;

    /**
     * @var boolean
     *
     * @ORM\Column(name="childrengames", type="boolean", nullable=true)
     */
    private $childrengames;

    /**
     * @var boolean
     *
     * @ORM\Column(name="outdoorparking", type="boolean", nullable=true)
     */
    private $outdoorparking;

    /**
     * @var boolean
     *
     * @ORM\Column(name="indoorparking", type="boolean", nullable=true)
     */
    private $indoorparking;

    /**
     * @var boolean
     *
     * @ORM\Column(name="gardenfurniture", type="boolean", nullable=true)
     */
    private $gardenfurniture;

    /**
     * @var boolean
     *
     * @ORM\Column(name="terrace", type="boolean", nullable=true)
     */
    private $terrace;

    /**
     * @var string
     *
     * @ORM\Column(name="other", type="string", length=50, nullable=true)
     */
    private $other;

    /**
     * @var string
     *
     * @ORM\Column(name="picture1", type="blob", nullable=true)
     */
    private $picture1;

    /**
     * @var string
     *
     * @ORM\Column(name="picture2", type="blob", nullable=true)
     */
    private $picture2;

    /**
     * @var string
     *
     * @ORM\Column(name="picture3", type="blob", nullable=true)
     */
    private $picture3;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_user", referencedColumnName="id")
     * })
     */
    private $fkUser;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="User", inversedBy="fkStayHouseid")
     * @ORM\JoinTable(name="stay",
     *   joinColumns={
     *     @ORM\JoinColumn(name="fk_stay_houseid", referencedColumnName="id")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="fk_stay_userid", referencedColumnName="id")
     *   }
     * )
     */
    private $fkStayUserid;

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->fkStayUserid = new \Doctrine\Common\Collections\ArrayCollection();
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
     * @return string
     */
    public function getNature()
    {
        return $this->nature;
    }

    /**
     * @param string $nature
     */
    public function setNature($nature)
    {
        $this->nature = $nature;
    }

    /**
     * @return int
     */
    public function getPrice()
    {
        return $this->price;
    }

    /**
     * @param int $price
     */
    public function setPrice($price)
    {
        $this->price = $price;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return int
     */
    public function getRating()
    {
        return $this->rating;
    }

    /**
     * @param int $rating
     */
    public function setRating($rating)
    {
        $this->rating = $rating;
    }

    /**
     * @return boolean
     */
    public function isAirconditioner()
    {
        return $this->airconditioner;
    }

    /**
     * @param boolean $airconditioner
     */
    public function setAirconditioner($airconditioner)
    {
        $this->airconditioner = $airconditioner;
    }

    /**
     * @return boolean
     */
    public function isFireplace()
    {
        return $this->fireplace;
    }

    /**
     * @param boolean $fireplace
     */
    public function setFireplace($fireplace)
    {
        $this->fireplace = $fireplace;
    }

    /**
     * @return boolean
     */
    public function isWashingmachine()
    {
        return $this->washingmachine;
    }

    /**
     * @param boolean $washingmachine
     */
    public function setWashingmachine($washingmachine)
    {
        $this->washingmachine = $washingmachine;
    }

    /**
     * @return boolean
     */
    public function isDishwasher()
    {
        return $this->dishwasher;
    }

    /**
     * @param boolean $dishwasher
     */
    public function setDishwasher($dishwasher)
    {
        $this->dishwasher = $dishwasher;
    }

    /**
     * @return boolean
     */
    public function isDvd()
    {
        return $this->dvd;
    }

    /**
     * @param boolean $dvd
     */
    public function setDvd($dvd)
    {
        $this->dvd = $dvd;
    }

    /**
     * @return boolean
     */
    public function isIndoorpool()
    {
        return $this->indoorpool;
    }

    /**
     * @param boolean $indoorpool
     */
    public function setIndoorpool($indoorpool)
    {
        $this->indoorpool = $indoorpool;
    }

    /**
     * @return boolean
     */
    public function isSauna()
    {
        return $this->sauna;
    }

    /**
     * @param boolean $sauna
     */
    public function setSauna($sauna)
    {
        $this->sauna = $sauna;
    }

    /**
     * @return boolean
     */
    public function isJacuzzi()
    {
        return $this->jacuzzi;
    }

    /**
     * @param boolean $jacuzzi
     */
    public function setJacuzzi($jacuzzi)
    {
        $this->jacuzzi = $jacuzzi;
    }

    /**
     * @return boolean
     */
    public function isTv()
    {
        return $this->tv;
    }

    /**
     * @param boolean $tv
     */
    public function setTv($tv)
    {
        $this->tv = $tv;
    }

    /**
     * @return boolean
     */
    public function isElevator()
    {
        return $this->elevator;
    }

    /**
     * @param boolean $elevator
     */
    public function setElevator($elevator)
    {
        $this->elevator = $elevator;
    }

    /**
     * @return boolean
     */
    public function isBarbecue()
    {
        return $this->barbecue;
    }

    /**
     * @param boolean $barbecue
     */
    public function setBarbecue($barbecue)
    {
        $this->barbecue = $barbecue;
    }

    /**
     * @return boolean
     */
    public function isGarden()
    {
        return $this->garden;
    }

    /**
     * @param boolean $garden
     */
    public function setGarden($garden)
    {
        $this->garden = $garden;
    }

    /**
     * @return boolean
     */
    public function isChildrengames()
    {
        return $this->childrengames;
    }

    /**
     * @param boolean $childrengames
     */
    public function setChildrengames($childrengames)
    {
        $this->childrengames = $childrengames;
    }

    /**
     * @return boolean
     */
    public function isOutdoorparking()
    {
        return $this->outdoorparking;
    }

    /**
     * @param boolean $outdoorparking
     */
    public function setOutdoorparking($outdoorparking)
    {
        $this->outdoorparking = $outdoorparking;
    }

    /**
     * @return boolean
     */
    public function isIndoorparking()
    {
        return $this->indoorparking;
    }

    /**
     * @param boolean $indoorparking
     */
    public function setIndoorparking($indoorparking)
    {
        $this->indoorparking = $indoorparking;
    }

    /**
     * @return boolean
     */
    public function isGardenfurniture()
    {
        return $this->gardenfurniture;
    }

    /**
     * @param boolean $gardenfurniture
     */
    public function setGardenfurniture($gardenfurniture)
    {
        $this->gardenfurniture = $gardenfurniture;
    }

    /**
     * @return boolean
     */
    public function isTerrace()
    {
        return $this->terrace;
    }

    /**
     * @param boolean $terrace
     */
    public function setTerrace($terrace)
    {
        $this->terrace = $terrace;
    }

    /**
     * @return string
     */
    public function getOther()
    {
        return $this->other;
    }

    /**
     * @param string $other
     */
    public function setOther($other)
    {
        $this->other = $other;
    }

    /**
     * @return string
     */
    public function getPicture1()
    {
        return $this->picture1;
    }

    /**
     * @param string $picture1
     */
    public function setPicture1($picture1)
    {
        $this->picture1 = $picture1;
    }

    /**
     * @return string
     */
    public function getPicture2()
    {
        return $this->picture2;
    }

    /**
     * @param string $picture2
     */
    public function setPicture2($picture2)
    {
        $this->picture2 = $picture2;
    }

    /**
     * @return string
     */
    public function getPicture3()
    {
        return $this->picture3;
    }

    /**
     * @param string $picture3
     */
    public function setPicture3($picture3)
    {
        $this->picture3 = $picture3;
    }

    /**
     * @return \User
     */
    public function getFkUser()
    {
        return $this->fkUser;
    }

    /**
     * @param \User $fkUser
     */
    public function setFkUser($fkUser)
    {
        $this->fkUser = $fkUser;
    }

    /**
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getFkStayUserid()
    {
        return $this->fkStayUserid;
    }

    /**
     * @param \Doctrine\Common\Collections\Collection $fkStayUserid
     */
    public function setFkStayUserid($fkStayUserid)
    {
        $this->fkStayUserid = $fkStayUserid;
    }



}

