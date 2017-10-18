<?php
/**
 * Created by PhpStorm.
 * User: grami
 * Date: 30/11/2016
 * Time: 17:03
 */

namespace AdminBundle\Controller;


use AdminBundle\Entity\Notification;
use AdminBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Validator\Constraints\DateTime;

class msgreportController extends Controller
{

    public function declinemsgreportAction($i,$id){
        $em=$this->getDoctrine()->getManager();

        $mrs=$em->getRepository('AdminBundle:Messagereport')->findBy(array('fkMessage'=>$i));
        foreach ($mrs as $m){
            $m->setIstreated(true);
            $em->persist($m);
        }
        $em->flush();

        $rmessages=$em->getRepository('AdminBundle:Messagereport')->findBy(array('istreated'=>"0"));
        $forums=array();
        $i=0;
        foreach ($rmessages as $m){
            if(!in_array($m->getFkMessage()->getFkMessageForumid(),$forums)){
                $forums[$i]=$m->getFkMessage()->getFkMessageForumid();
                $i++;
            }
        }
        $reported = array();
        foreach ($rmessages as $m){
            array_push($reported,$m->getFkMessage());
        }
        $images=array();
        if($i!=0){
            if($id<0) {
                if(($forums[0]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[0]->getFkForumOwnerid()->getId(),$images)))
                    $images[$forums[0]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[0]->getFkForumOwnerid()->getPicture()));
                $messages = $em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid' => $forums[0]->getId()));
                return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => 1, 'forum' => $forums[0],'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages,'images'=>$images));
            }if($id >= $i) {
                if(($forums[$i-1]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[$i-1]->getFkForumOwnerid()->getId(),$images)))
                    $images[$forums[$i-1]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[$i-1]->getFkForumOwnerid()->getPicture()));
                $messages =$em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid'=>$forums[$i-1]->getId()));

                return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => $i, 'forum' => $forums[$i - 1],'images'=>$images,'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages));
            }
            if(($forums[$id]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[$id]->getFkForumOwnerid()->getId(),$images)))
                $images[$forums[$id]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[$id]->getFkForumOwnerid()->getPicture()));

            $messages =$em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid'=>$forums[$id]->getId()));
            return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => $id + 1, 'forum' => $forums[$id],'images'=>$images,'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages));

        }
        else {
            return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => null, 'forum' => null,'messages'=>null,'images'=>null,'reported'=>null,'msgreports'=>null));
        }
    }
    public function deletemsgAction($i,$id){
        $em=$this->getDoctrine()->getManager();

        $m=$em->getRepository('AdminBundle:Message')->find($i);
            $em->remove($m);
        $em->flush();

        $rmessages=$em->getRepository('AdminBundle:Messagereport')->findBy(array('istreated'=>"0"));
        $forums=array();
        $i=0;
        foreach ($rmessages as $m){
            if(!in_array($m->getFkMessage()->getFkMessageForumid(),$forums)){
                $forums[$i]=$m->getFkMessage()->getFkMessageForumid();
                $i++;
            }
        }
        $reported = array();
        foreach ($rmessages as $m){
            array_push($reported,$m->getFkMessage());
        }
        $images=array();
        if($i!=0){
            if($id<0) {
                if(($forums[0]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[0]->getFkForumOwnerid()->getId(),$images)))
                    $images[$forums[0]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[0]->getFkForumOwnerid()->getPicture()));
                $messages = $em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid' => $forums[0]->getId()));
                return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => 1, 'forum' => $forums[0],'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages,'images'=>$images));
            }if($id >= $i) {
                if(($forums[$i-1]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[$i-1]->getFkForumOwnerid()->getId(),$images)))
                    $images[$forums[$i-1]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[$i-1]->getFkForumOwnerid()->getPicture()));
                $messages =$em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid'=>$forums[$i-1]->getId()));

                return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => $i, 'forum' => $forums[$i - 1],'images'=>$images,'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages));
            }
            if(($forums[$id]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[$id]->getFkForumOwnerid()->getId(),$images)))
                $images[$forums[$id]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[$id]->getFkForumOwnerid()->getPicture()));

            $messages =$em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid'=>$forums[$id]->getId()));
            return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => $id + 1, 'forum' => $forums[$id],'images'=>$images,'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages));

        }
        else {
            return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => null, 'forum' => null,'messages'=>null,'images'=>null,'reported'=>null,'msgreports'=>null));
        }
    }

    public function banuserAction($i,$id){
        $em=$this->getDoctrine()->getManager();

        $mrs=$em->getRepository('AdminBundle:Messagereport')->findBy(array('fkMessage'=>$i));
        foreach ($mrs as $m){
            $m->setIstreated(true);
            $em->persist($m);
        }

        $m=$em->getRepository('AdminBundle:Message')->find($i);
        $u =$m->getFkMessageOwnerid();
        $a=array();
        foreach ($u->getFkForum() as $f)
            array_push($a,$f);

        array_push($a,$m->getFkMessageForumid());
        $u->setFkForum($a);
        $em->persist($u);
        $em->flush();


        $rmessages=$em->getRepository('AdminBundle:Messagereport')->findBy(array('istreated'=>"0"));
        $forums=array();
        $i=0;
        foreach ($rmessages as $m){
            if(!in_array($m->getFkMessage()->getFkMessageForumid(),$forums)){
                $forums[$i]=$m->getFkMessage()->getFkMessageForumid();
                $i++;
            }
        }
        $reported = array();
        foreach ($rmessages as $m){
            array_push($reported,$m->getFkMessage());
        }
        $images=array();
        if($i!=0){
            if($id<0) {
                if(($forums[0]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[0]->getFkForumOwnerid()->getId(),$images)))
                    $images[$forums[0]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[0]->getFkForumOwnerid()->getPicture()));
                $messages = $em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid' => $forums[0]->getId()));
                return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => 1, 'forum' => $forums[0],'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages,'images'=>$images));
            }if($id >= $i) {
                if(($forums[$i-1]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[$i-1]->getFkForumOwnerid()->getId(),$images)))
                    $images[$forums[$i-1]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[$i-1]->getFkForumOwnerid()->getPicture()));
                $messages =$em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid'=>$forums[$i-1]->getId()));

                return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => $i, 'forum' => $forums[$i - 1],'images'=>$images,'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages));
            }
            if(($forums[$id]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[$id]->getFkForumOwnerid()->getId(),$images)))
                $images[$forums[$id]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[$id]->getFkForumOwnerid()->getPicture()));

            $messages =$em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid'=>$forums[$id]->getId()));
            return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => $id + 1, 'forum' => $forums[$id],'images'=>$images,'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages));

        }
        else {
            return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => null, 'forum' => null,'messages'=>null,'images'=>null,'reported'=>null,'msgreports'=>null));
        }
    }
    public function notifyuserAction($i,$id){
        $em=$this->getDoctrine()->getManager();

        $mrs=$em->getRepository('AdminBundle:Messagereport')->findBy(array('fkMessage'=>$i));
        foreach ($mrs as $m){
            $m->setIstreated(true);
            $em->persist($m);
        }

        $m=$em->getRepository('AdminBundle:Message')->find($i);
        $n=new Notification();
        $n->setText("your message ".$m->getText()." has been reported and we are warning you of transpassing rules");
        $d = new \DateTime();

        $n->setDate($d);
        $n->setFkNotifUserid($m->getFkMessageOwnerid());

        $em->persist($n);
        $em->remove($m);
        $em->flush();

        $rmessages=$em->getRepository('AdminBundle:Messagereport')->findBy(array('istreated'=>"0"));
        $forums=array();
        $i=0;
        foreach ($rmessages as $m){
            if(!in_array($m->getFkMessage()->getFkMessageForumid(),$forums)){
                $forums[$i]=$m->getFkMessage()->getFkMessageForumid();
                $i++;
            }
        }
        $reported = array();
        foreach ($rmessages as $m){
            array_push($reported,$m->getFkMessage());
        }
        $images=array();
        if($i!=0){
            if($id<0) {
                if(($forums[0]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[0]->getFkForumOwnerid()->getId(),$images)))
                    $images[$forums[0]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[0]->getFkForumOwnerid()->getPicture()));
                $messages = $em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid' => $forums[0]->getId()));
                return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => 1, 'forum' => $forums[0],'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages,'images'=>$images));
            }if($id >= $i) {
                if(($forums[$i-1]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[$i-1]->getFkForumOwnerid()->getId(),$images)))
                    $images[$forums[$i-1]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[$i-1]->getFkForumOwnerid()->getPicture()));
                $messages =$em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid'=>$forums[$i-1]->getId()));

                return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => $i, 'forum' => $forums[$i - 1],'images'=>$images,'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages));
            }
            if(($forums[$id]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[$id]->getFkForumOwnerid()->getId(),$images)))
                $images[$forums[$id]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[$id]->getFkForumOwnerid()->getPicture()));

            $messages =$em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid'=>$forums[$id]->getId()));
            return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => $id + 1, 'forum' => $forums[$id],'images'=>$images,'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages));

        }
        else {
            return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => null, 'forum' => null,'messages'=>null,'images'=>null,'reported'=>null,'msgreports'=>null));
        }
    }
    public function deletemsgownerAction($i,$id){
        $em=$this->getDoctrine()->getManager();

        $m=$em->getRepository('AdminBundle:Message')->find($i);
        $em->remove($m->getFkMessageOwnerid());
        $em->flush();

        $rmessages=$em->getRepository('AdminBundle:Messagereport')->findBy(array('istreated'=>"0"));
        $forums=array();
        $i=0;
        foreach ($rmessages as $m){
            if(!in_array($m->getFkMessage()->getFkMessageForumid(),$forums)){
                $forums[$i]=$m->getFkMessage()->getFkMessageForumid();
                $i++;
            }
        }
        $reported = array();
        foreach ($rmessages as $m){
            array_push($reported,$m->getFkMessage());
        }
        $images=array();
        if($i!=0){
            if($id<0) {
                if(($forums[0]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[0]->getFkForumOwnerid()->getId(),$images)))
                    $images[$forums[0]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[0]->getFkForumOwnerid()->getPicture()));
                $messages = $em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid' => $forums[0]->getId()));
                return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => 1, 'forum' => $forums[0],'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages,'images'=>$images));
            }if($id >= $i) {
                if(($forums[$i-1]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[$i-1]->getFkForumOwnerid()->getId(),$images)))
                    $images[$forums[$i-1]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[$i-1]->getFkForumOwnerid()->getPicture()));
                $messages =$em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid'=>$forums[$i-1]->getId()));

                return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => $i, 'forum' => $forums[$i - 1],'images'=>$images,'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages));
            }
            if(($forums[$id]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[$id]->getFkForumOwnerid()->getId(),$images)))
                $images[$forums[$id]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[$id]->getFkForumOwnerid()->getPicture()));

            $messages =$em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid'=>$forums[$id]->getId()));
            return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => $id + 1, 'forum' => $forums[$id],'images'=>$images,'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages));

        }
        else {
            return $this->render('AdminBundle:messages:rmessages.html.twig', array('pages' => $i, 'page' => null, 'forum' => null,'messages'=>null,'images'=>null,'reported'=>null,'msgreports'=>null));
        }
    }
}