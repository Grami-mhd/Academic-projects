<?php
/**
 * Created by PhpStorm.
 * User: grami
 * Date: 29/11/2016
 * Time: 00:04
 */

namespace AdminBundle\Controller;

use AppBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;

class UsersController extends Controller
{

    function searchAction($key){
        $em = $this->getDoctrine()->getManager();
        if($key=="")
            $users=$em->getRepository('AdminBundle:User')->findAll();
        else
        $users=$em->getRepository('AdminBundle:User')->findlike($key);

        $images =array();
        foreach ($users as $key => $user){
            if($user->getPicture() != null)
                $images[$user->getId()] = base64_encode(stream_get_contents( $user->getPicture()));
        }
        return $this->render('AdminBundle:users:users.html.twig',array('users'=>$users, 'pictures'=>$images));

    }
    function toadminAction($id){
        $em= $this->getDoctrine()->getManager();
        $u=$em->getRepository('AppBundle:User')->find($id);
        $u->addRole("role_admin");
        $em->persist($u);
        $em->flush();
        return $this->render('AdminBundle:users:toadmin.html.twig',array('u'=>$u));
    }
    function unadminAction($id){
        $em= $this->getDoctrine()->getManager();
        $u=$em->getRepository('AppBundle:User')->find($id);
        $u->removeRole("role_admin");
        $em->persist($u);
        $em->flush();
        return $this->render('AdminBundle:users:unadmin.html.twig',array('u'=>$u));
    }
}