<?php
/**
 * Created by PhpStorm.
 * User: grami
 * Date: 29/11/2016
 * Time: 12:02
 */

namespace AdminBundle\Controller;


use AppBundle\Entity\Forum;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class demendsController extends Controller
{

    function acceptAction($id){
        $em=$this->getDoctrine()->getManager();
        $fo=$forums=$em->getRepository('AppBundle:Forum')->find($id);

        $fo->setIscreated(true);
        $em->persist($fo);
        $em->flush();


        $forums=$em->getRepository('AppBundle:Forum')->findBy(array('iscreated' => "0"));
        $images =array();
        foreach ($forums as $key => $f){
            if(($f->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($f->getFkForumOwnerid()->getId(),$images)))
                $images[$f->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $f->getFkForumOwnerid()->getPicture()));
        }
        $other = array();
        $members=array();
        $numbers=array();
        foreach ($forums as $f){
            $other[$f->getFkForumOwnerid()->getId()]=$em->getRepository('AppBundle:Forum')->findBy(array('fkForumOwnerid' => $f->getFkForumOwnerid()->getId(),'iscreated'=>"1"));
            foreach ($other as $ofl){
                foreach ($ofl as $of){
                    $members[$of->getId()]=$em->getRepository('AppBundle:Message')->findBy(array('fkMessageForumid' =>$of->getId()));
                    $numbers[$of->getId()]=count($members[$of->getId()]);
                }
            }

        }

        return $this->render('AdminBundle:forums:fdemends.html.twig',array('forums'=>$forums , 'images'=>$images,'other'=>$other , 'numbers'=>$numbers));

    }
    function declineAction($id){

        $em=$this->getDoctrine()->getManager();
        $fo=$forums=$em->getRepository('AdminBundle:Forum')->find($id);
        $em->remove($fo);
        $em->flush();

        $forums=$em->getRepository('AdminBundle:Forum')->findBy(array('iscreated' => "0"));
        $images =array();
        foreach ($forums as $key => $f){
            if(($f->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($f->getFkForumOwnerid()->getId(),$images)))
                $images[$f->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $f->getFkForumOwnerid()->getPicture()));
        }
        $other = array();
        $members=array();
        $numbers=array();
        foreach ($forums as $f){
            $other[$f->getFkForumOwnerid()->getId()]=$em->getRepository('AppBundle:Forum')->findBy(array('fkForumOwnerid' => $f->getFkForumOwnerid()->getId(),'iscreated'=>"1"));
            foreach ($other as $ofl){
                foreach ($ofl as $of){
                    $members[$of->getId()]=$em->getRepository('AppBundle:Message')->findBy(array('fkMessageForumid' =>$of->getId()));
                    $numbers[$of->getId()]=count($members[$of->getId()]);
                }
            }

        }

        return $this->render('AdminBundle:forums:fdemends.html.twig',array('forums'=>$forums , 'images'=>$images,'other'=>$other , 'numbers'=>$numbers));

    }
    function declineallAction(){

        $em=$this->getDoctrine()->getManager();
        $forums=$em->getRepository('AdminBundle:Forum')->findBy(array('iscreated' => "0"));
        $images =array();
        foreach ($forums as $key => $f){
            $em->remove($f);
        }
        $em->flush();
        $forums=$em->getRepository('AdminBundle:Forum')->findBy(array('iscreated' => "0"));
        $images =array();
        foreach ($forums as $key => $f){
            if(($f->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($f->getFkForumOwnerid()->getId(),$images)))
                $images[$f->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $f->getFkForumOwnerid()->getPicture()));
        }
        $other = array();
        $members=array();
        $numbers=array();
        foreach ($forums as $f){
            $other[$f->getFkForumOwnerid()->getId()]=$em->getRepository('AppBundle:Forum')->findBy(array('fkForumOwnerid' => $f->getFkForumOwnerid()->getId(),'iscreated'=>"1"));
            foreach ($other as $ofl){
                foreach ($ofl as $of){
                    $members[$of->getId()]=$em->getRepository('AppBundle:Message')->findBy(array('fkMessageForumid' =>$of->getId()));
                    $numbers[$of->getId()]=count($members[$of->getId()]);
                }
            }

        }

        return $this->render('AdminBundle:forums:fdemends.html.twig',array('forums'=>$forums , 'images'=>$images,'other'=>$other , 'numbers'=>$numbers));
    }
    function acceptallAction(){

        $em=$this->getDoctrine()->getManager();
        $forums=$em->getRepository('AdminBundle:Forum')->findBy(array('iscreated' => "0"));
        $images =array();
        foreach ($forums as $key => $f){
            $f->setIscreated(true);
            $em->persist($f);
        }
        $em->flush();

        $forums=$em->getRepository('AdminBundle:Forum')->findBy(array('iscreated' => "0"));
        $images =array();
        foreach ($forums as $key => $f){
            if(($f->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($f->getFkForumOwnerid()->getId(),$images)))
                $images[$f->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $f->getFkForumOwnerid()->getPicture()));
        }
        $other = array();
        $members=array();
        $numbers=array();
        foreach ($forums as $f){
            $other[$f->getFkForumOwnerid()->getId()]=$em->getRepository('AppBundle:Forum')->findBy(array('fkForumOwnerid' => $f->getFkForumOwnerid()->getId(),'iscreated'=>"1"));
            foreach ($other as $ofl){
                foreach ($ofl as $of){
                    $members[$of->getId()]=$em->getRepository('AppBundle:Message')->findBy(array('fkMessageForumid' =>$of->getId()));
                    $numbers[$of->getId()]=count($members[$of->getId()]);
                }
            }

        }

        return $this->render('AdminBundle:forums:fdemends.html.twig',array('forums'=>$forums , 'images'=>$images,'other'=>$other , 'numbers'=>$numbers));
    }
}