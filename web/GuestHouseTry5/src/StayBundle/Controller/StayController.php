<?php

namespace StayBundle\Controller;

use StayBundle\Entity\Comment;
use StayBundle\Entity\Rate;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class StayController extends Controller
{
    public function indexAction($name)
    {
        return $this->render('', array('name' => $name));
    }

    public function afficheAction(){
        $em = $this->getDoctrine()->getManager();
        $u = $this->getUser();
        $user = $em -> getRepository('StayBundle:User')->find($u->getId());
        $mr1 = $em->getRepository("StayBundle:Rate")->findBy(array('fkRateOwnerid'=>$this->getUser()->getId()));
        $mr = array();
        foreach ($mr1 as $m){
            $mr[$m->getFkRateHouseid()->getId()]=$m->getRate();
        }

        $images = array();
        foreach ($user->getFkStayHouseid() as $hous){
            $house=array();
            if($hous->getPicture1()!= null)
                array_push($house,base64_encode(stream_get_contents($hous->getPicture1())));
            if($hous->getPicture2()!= null)
                array_push($house,base64_encode(stream_get_contents($hous->getPicture2())));
            if($hous->getPicture3()!= null)
                array_push($house,base64_encode(stream_get_contents($hous->getPicture3())));

            $images[$hous->getId()]=$house;
        }

        return $this->render('StayBundle:Stay:evaluateStay.html.twig',array('u'=>$user,'img'=>$images,'mrs'=>$mr));
    }

    public function finishAction($id,Request $request){
        $em = $this->getDoctrine()->getManager();
        $dates = $em-> getRepository('StayBundle:Dates')->findBy(array('fkAddid'=>$id));

        $u = $this->getUser();
        $user = $em -> getRepository('StayBundle:User')->find($u->getId());
        $house= $em -> getRepository('StayBundle:House')->find($id);

        if($request->isMethod('post')){
            $s=0;
            $i=0;
            foreach ($dates as $day){
                if($request->get("".$i)){
                    $s+=$house->getPrice();
                }
                $i++;
            }
            $user->setPoints($user->getPoints()-$s);
            $em->persist($user);
            $em->flush();

        }

        $hous=array();
        if($house->getPicture1()!= null)
            array_push($hous,base64_encode(stream_get_contents($house->getPicture1())));
        if($house->getPicture2()!= null)
            array_push($hous,base64_encode(stream_get_contents($house->getPicture2())));
        if($house->getPicture3()!= null)
            array_push($hous,base64_encode(stream_get_contents($house->getPicture3())));

        return $this->render('StayBundle:Stay:finishReservation.html.twig',array('date'=>$dates,'img'=>$hous,'u'=>$user,'h'=>$house));

    }

    public function confirmAction($nbr){
        $u = $this->getUser();
        $pts = $u->getPoints();

        return $this->render('StayBundle:Stay:confirm.html.twig',array('reste'=>($pts-$nbr)));

    }

    public function addCommentAction($id,$cc){
        $u = $this->getUser();
        $em = $this->getDoctrine()->getManager();
        $u=$em->getRepository("StayBundle:User")->find($u);
        $h = $em -> getRepository('StayBundle:House')->find($id);
        $comment = new Comment();

        $comment -> setComment($cc);
        $comment -> setFkCommentOwnerid($u);
        $comment -> setFkCommentHouseid($h);
        $em->persist($comment);
        $em->flush();

        return $this->render("StayBundle:Stay:confirmComment.html.twig",array('txt'=>$cc));

    }

    public function ratingAction($id,$r){
        $em = $this->getDoctrine()->getManager();
        $rate = $em->getRepository("StayBundle:Rate")->findOneBy(array('fkRateOwnerid'=>$this->getUser()->getId(),'fkRateHouseid'=>$id));
        if($rate == null){
            $rate = new Rate();
            $rate->setRate($r);
            $rate->setFkRateHouseid($em -> getRepository('StayBundle:House')->find($id));
            $rate->setFkRateOwnerid($em -> getRepository('StayBundle:User')->find($this->getUser()->getId()));

        }else{
            $rate->setRate($r);
        }

        $em->persist($rate);
        $em->flush();


        $rates = $em->getRepository("StayBundle:Rate")->findBy(array('fkRateHouseid'=>$id));
        $somme=0;
        $i=0;
        foreach ($rates as $r){
            $somme+=$r->getRate();
            $i++;
        }
        $h=$em->getRepository("StayBundle:House")->find($id);
        $h->setRating(round($somme/$i));
        $em->persist($h);
        $em->flush();

        $u = $this->getUser();
        $mr1 = $em->getRepository("StayBundle:Rate")->findBy(array('fkRateOwnerid'=>$this->getUser()->getId()));
        $mr = array();
        foreach ($mr1 as $m){
            $mr[$m->getFkRateHouseid()->getId()]=$m->getRate();
        }


        $user = $em -> getRepository('StayBundle:User')->find($u->getId());

        $images = array();
        foreach ($user->getFkStayHouseid() as $hous){
            $house=array();
            if($hous->getPicture1()!= null)
                array_push($house,base64_encode(stream_get_contents($hous->getPicture1())));
            if($hous->getPicture2()!= null)
                array_push($house,base64_encode(stream_get_contents($hous->getPicture2())));
            if($hous->getPicture3()!= null)
                array_push($house,base64_encode(stream_get_contents($hous->getPicture3())));

            $images[$hous->getId()]=$house;
        }


        return $this->render('StayBundle:Stay:rating.html.twig',array('u'=>$user,'img'=>$images,'mrs'=>$mr,'rates'=>$rates));

    }

    public function hostingAction(){
        $em = $this->getDoctrine()->getManager();
        $hosting = $em-> getRepository('StayBundle:Hostingdemande')->findBy(array('fkReceiverid'=>$this->getUser()->getId()));

        $u = $this->getUser();
        $user = $em -> getRepository('StayBundle:User')->find($u->getId());

        return $this->render('StayBundle:Stay:hostingDemande.html.twig',array('houses'=>$hosting,'user'=>$user));

    }

}
