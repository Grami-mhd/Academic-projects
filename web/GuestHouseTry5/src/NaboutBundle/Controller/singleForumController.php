<?php

namespace NaboutBundle\Controller;

use AppBundle\Entity\Message;
use AppBundle\Entity\Messagereport;
use AppBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class singleForumController extends Controller
{
    public function singleForumAction($id,Request $request)
    {
        $em=$this->getDoctrine()->getManager();

        $u=$this->getUser();
        $forum=$em->getRepository('AppBundle:Forum')->find($id);


        if($request->isMethod('POST')){

            $message=new Message();
            $message->setText($request->get('comment'));
            $message->setFkMessageForumid($em->getRepository('AppBundle:Forum')->find($id));

            $message->setFkMessageOwnerid($u);
            $em->persist($message);
            $em->flush();
        }

        $mymessages=array();
        $othermsg=array();

        $messages=$em->getRepository('AppBundle:Message')->findBy(array('fkMessageForumid' => $forum->getId()));
        foreach ($messages as $m){

            $Rmessage=$em->getRepository('AppBundle:Messagereport')->findOneBy(array('fkReporter' => $u,'fkMessage'=>$m));
            if($Rmessage!=null){
                $rm=$Rmessage->getFkMessage();
                if(($key = array_search($rm,$messages)) != false){
                    unset($messages[$key]);
                }
                //if($rm->getFkMessageForumid()==$forum)
                //array_diff($messages,[$rm]);
            }
        }

        foreach ($messages as $m){

                if($m->getFkMessageOwnerid()==$u){
                    array_push($mymessages,$m);
                }

                else{
                    array_push($othermsg, $m);
                }


        }
        $nbrMsg=count($messages);

        if ($forum->getFkForumOwnerid() == $u) {
            return $this->render('NaboutBundle:singleForum:myownForum.html.twig',array('f'=>$forum,'messages'=>$messages,'nbr'=>$nbrMsg,'othermsg'=>$othermsg,'mymessages'=>$mymessages));
        }
        if($forum!=null){
            return $this->render('NaboutBundle:singleForum:singleForum.html.twig',array('forum'=>$forum ,'messages'=>$messages,'nbr'=>$nbrMsg,'othermsg'=>$othermsg,'mymessages'=>$mymessages));
        }

    }

    public function editForumAction($id,$txt){

        $em=$this->getDoctrine()->getManager();
        $forum=$em->getRepository('AppBundle:Forum')->find($id);

        $forum->setSubject($txt);

        $em->persist($forum);
        $em->flush();
        return $this->render('NaboutBundle:singleForum:confirm.html.twig');
    }

    public function editmsgAction($id,$txt){

        $em=$this->getDoctrine()->getManager();
        $message=$em->getRepository('AppBundle:Message')->find($id);

        $message->setText($txt);

        $em->persist($message);
        $em->flush();
        $u=$this->getUser();
        $messages=$em->getRepository('AppBundle:Message')->findBy(array('fkMessageForumid' => $message->getFkMessageForumid()->getId()));

        $mymessages=array();
        $othermsg=array();

        foreach ($messages as $m){

            $Rmessage=$em->getRepository('AppBundle:Messagereport')->findOneBy(array('fkReporter' => $u,'fkMessage'=>$m));
            if($Rmessage!=null){
                $rm=$Rmessage->getFkMessage();
                if(($key = array_search($rm,$messages)) != false){
                    unset($messages[$key]);
                }
                //if($rm->getFkMessageForumid()==$forum)
                //array_diff($messages,[$rm]);
            }
        }

        foreach ($messages as $m){

            if($m->getFkMessageOwnerid()==$u){
                array_push($mymessages,$m);
            }

            else{
                array_push($othermsg, $m);
            }


        }
        return $this->render('NaboutBundle:singleForum:confirmeditingmsg.html.twig',array('messages'=>$messages,'mymessages'=>$mymessages));
    }

    public  function deletemsgAction($id){

        $em=$this->getDoctrine()->getManager();
        $m=$em->getRepository('AppBundle:Message')->find($id);


        $u=$this->getUser();
        $messages=$em->getRepository('AppBundle:Message')->findBy(array('fkMessageForumid' => $m->getFkMessageForumid()->getId()));

        $mymessages=array();
        $othermsg=array();

        foreach ($messages as $m){

            $Rmessage=$em->getRepository('AppBundle:Messagereport')->findOneBy(array('fkReporter' => $u,'fkMessage'=>$m));
            if($Rmessage!=null){
                $rm=$Rmessage->getFkMessage();
                if(($key = array_search($rm,$messages)) != false){
                    unset($messages[$key]);
                }
                //if($rm->getFkMessageForumid()==$forum)
                //array_diff($messages,[$rm]);
            }
        }

        foreach ($messages as $m){
            if($m->getFkMessageOwnerid()==$u){
                array_push($mymessages,$m);
            }
            else{
                array_push($othermsg, $m);
            }
        }
        $em->remove($m);
        $em->flush();
        return $this->render('NaboutBundle:singleForum:confirmdeletingmsg.html.twig',array('messages'=>$messages,'mymessages'=>$mymessages));
    }

    public  function reportmsgAction($id){
        $u=$this->getUser();

        $em=$this->getDoctrine()->getManager();
        $m1=$em->getRepository('AppBundle:Message')->find($id);

        $mr=new Messagereport();
        $mr->setFkMessage($m1);
        $mr->setFkReporter($u);

        $em->persist($mr);
        $em->flush();

        $mymessages=array();
        $othermsg=array();

        $messages=$em->getRepository('AppBundle:Message')->findBy(array('fkMessageForumid' => $m1->getFkMessageForumid()->getId()));
        foreach ($messages as $m){

            $Rmessage=$em->getRepository('AppBundle:Messagereport')->findOneBy(array('fkReporter' => $u,'fkMessage'=>$m));
            if($Rmessage!=null){
                $rm=$Rmessage->getFkMessage();
                if(($key = array_search($rm,$messages)) != false){
                    unset($messages[$key]);
                }
                //if($rm->getFkMessageForumid()==$forum)
                //array_diff($messages,[$rm]);
            }
        }



        foreach ($messages as $m){
            if($m->getFkMessageOwnerid()==$u){
                array_push($mymessages,$m);
            }
            else{
                array_push($othermsg, $m);
            }
        }

        return $this->render('NaboutBundle:singleForum:confirmreportingmsg.html.twig',array('messages'=>$messages,'mymessages'=>$mymessages));
    }


}



