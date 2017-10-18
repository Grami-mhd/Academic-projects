<?php
/**
 * Created by PhpStorm.
 * User: grami
 * Date: 29/11/2016
 * Time: 21:11
 */

namespace AdminBundle\Controller;


use AdminBundle\Entity\Forum;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class rforumController extends Controller
{

    public function declinereportforumAction($id){
        $em=$this->getDoctrine()->getManager();
        $f=$em->getRepository('AdminBundle:Forum')->find($id);
        $a=array();
        $f->setFkForumReportUserid($a);
        $em->persist($f);
        $em->flush();


        $allforums=$em->getRepository('AppBundle:Forum')->findAll();
        $forums = array();
        foreach ($allforums as $forum){
            if(count($forum->getFkForumReportUserid()) != 0  ){
                array_push($forums,$forum);
            }
        }

        $images =array();
        foreach ($forums as $key => $f){
            if(($f->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($f->getFkForumOwnerid()->getId(),$images)))
                $images[$f->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $f->getFkForumOwnerid()->getPicture()));
        }
        return $this->render('AdminBundle:forums:rforums.html.twig',array('forums'=>$forums,'images'=>$images));


    }
    public function deleteforumAction($id){

        $em=$this->getDoctrine()->getManager();
        $f=$em->getRepository('AdminBundle:Forum')->find($id);
        $em->remove($f);
        $em->flush();


        $allforums=$em->getRepository('AppBundle:Forum')->findAll();
        $forums = array();
        foreach ($allforums as $forum){
            if(count($forum->getFkForumReportUserid()) != 0  ){
                array_push($forums,$forum);
            }
        }

        $images =array();
        foreach ($forums as $key => $f){
            if(($f->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($f->getFkForumOwnerid()->getId(),$images)))
                $images[$f->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $f->getFkForumOwnerid()->getPicture()));
        }
        return $this->render('AdminBundle:forums:rforums.html.twig',array('forums'=>$forums,'images'=>$images));

    }
    public function deleteforumownerAction($id){
        $em=$this->getDoctrine()->getManager();
        $f=$em->getRepository('AdminBundle:Forum')->find($id);
        $em->remove($f->getFkForumOwnerid());
        $em->flush();


        $allforums=$em->getRepository('AppBundle:Forum')->findAll();
        $forums = array();
        foreach ($allforums as $forum){
            if(count($forum->getFkForumReportUserid()) != 0  ){
                array_push($forums,$forum);
            }
        }

        $images =array();
        foreach ($forums as $key => $f){
            if(($f->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($f->getFkForumOwnerid()->getId(),$images)))
                $images[$f->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $f->getFkForumOwnerid()->getPicture()));
        }
        return $this->render('AdminBundle:forums:rforums.html.twig',array('forums'=>$forums,'images'=>$images));

    }
}