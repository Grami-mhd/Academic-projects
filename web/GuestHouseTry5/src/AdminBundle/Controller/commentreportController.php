<?php
/**
 * Created by PhpStorm.
 * User: grami
 * Date: 30/11/2016
 * Time: 22:23
 */

namespace AdminBundle\Controller;


use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class commentreportController extends Controller
{

    public function deletecommentownerAction($id){
        $em=$this->getDoctrine()->getManager();
        $c=$em->getRepository('AdminBundle:Comment')->find($id);
        $em->remove($c->getFkCommentOwnerid());
        $em->flush();

        return $this->go();
    }
    public function deletecommentadminAction($id){
        $em=$this->getDoctrine()->getManager();
        $c=$em->getRepository('AdminBundle:Comment')->find($id);
        $em->remove($c);
        $em->flush();

        return $this->go();
    }
    public function declinecommentreportAction($id){

        $em=$this->getDoctrine()->getManager();
        $c=$em->getRepository('AdminBundle:Comment')->find($id);
        $a=array();
        $c->setFkCommentReportOwnerid($a);
        $em->persist($c);
        $em->flush();

        return $this->go();
    }

    public function go(){
        $em =$this->getDoctrine()->getManager();
        $allcomments=$em->getRepository('AdminBundle:Comment')->findAll();
        $comments = array();

        foreach ($allcomments as $comment){
            if(count($comment->getFkCommentReportOwnerid()) != 0  ){
                array_push($comments,$comment);
            }
        }

        $images =array();
        foreach ($comments as $c){
            if(($c->getFkCommentOwnerid()->getPicture() != null)&&(! array_key_exists($c->getFkCommentOwnerid()->getId(),$images)))
                $images[$c->getFkCommentOwnerid()->getId()] = base64_encode(stream_get_contents( $c->getFkCommentOwnerid()->getPicture()));
        }
        return $this->render('AdminBundle:comments:rcomments.html.twig',array('comments'=>$comments,'images'=>$images));
    }
}