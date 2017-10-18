<?php

/**
 * Created by PhpStorm.
 * User: grami
 * Date: 29/11/2016
 * Time: 18:36
 */
namespace AdminBundle\Repository;
use \Doctrine\ORM\EntityRepository;

class UserRepository extends EntityRepository
{

    public function findlike($ke){
        $ke="%".$ke."%";
        $query=$this->getEntityManager()
            ->createQuery("SELECT u FROM AdminBundle:User u WHERE u.username LIKE ?1 OR u.firstname LIKE ?2 OR u.lastname LIKE ?3 ");
        $query->setParameters(array(1=>$ke,2=>$ke,3=>$ke));
        return $query->getResult();
    }
}