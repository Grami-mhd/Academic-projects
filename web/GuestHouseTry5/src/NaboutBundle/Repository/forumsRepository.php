<?php
namespace NaboutBundle\Repository;
use \Doctrine\ORM\EntityManager ;
use Doctrine\ORM\EntityRepository;

/**
 * Created by PhpStorm.
 * User: Mohamed
 * Date: 2016-11-30
 * Time: 1:06 AM
 */
class forumsRepository extends EntityRepository
{
    public function findforumlike($k){
        $k="%".$k."%";
        $query=$this->getEntityManager()->createQuery("SELECT f FROM AppBundle:Forum f WHERE f.subject LIKE ?1");
        $query->setParameters(array(1=>$k));
        return $query->getResult();
    }

}