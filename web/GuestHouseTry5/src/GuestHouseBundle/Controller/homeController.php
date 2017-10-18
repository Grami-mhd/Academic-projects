<?php

namespace GuestHouseBundle\Controller;
use AppBundle\AppBundle;
use AppBundle\Entity\Date;
use AppBundle\Entity\House;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class homeController extends Controller
{
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();
        $u=$this->getUser();
        $image= null;
        if($u!=null)
        if($u->getPicture() != null)
            $image = base64_encode(stream_get_contents( $u->getPicture()));

        $dates=$em->getRepository('AppBundle:Date')->findAll();

        $add=array();
        foreach ($dates as $date){
            $h=$date->getFkAddid();
            $add[$h->getId()]=$h;
        }
        $images=array();
        foreach ($add as $hous) {
            if ($hous->getPicture1() != null) {
                $images[$hous->getId()] = base64_encode(stream_get_contents($hous->getPicture1()));
            }
        }
            return $this->render('GuestHouseBundle:Home:home_u.html.twig', array('user' => $u, 'picture' => $image, 'picture1' => $images,'add'=>$add));
    }


    public function researchDateAction ($key){

        $em = $this->getDoctrine()->getManager();
        $date=$em->getRepository('AppBundle:Date')->findBy(array('date'=>$key));
        $addV=array();
        foreach ($date as $d){
            $h=$d->getFkAddid();
            array_push($addV,$h);

        }
        $images=array();
        foreach ($addV as $hous) {
            if ($hous->getPicture1() != null) {
                $images[$hous->getId()] = base64_encode(stream_get_contents($hous->getPicture1()));
            }
        }
        return $this->render('GuestHouseBundle:Home:addDateV.html.twig', array('add'=>$addV,'picture1'=>$images));
    }

    public function researchTownAction ($key){

    $em = $this->getDoctrine()->getManager();
    $date=$em->getRepository('AppBundle:Date')->findAll();

    $add1=array();
    foreach ($date as $d){
        $h=$d->getFkAddid();
        array_push($add1,$h);
    }

    $addT=array();
    foreach ($add1 as $c){
        if ($c->getTown()== $key){
            array_push($addT ,$c);
        }
    }

    $images=array();
    foreach ($addT as $hous) {
        if ($hous->getPicture1() != null) {
            $images[$hous->getId()] = base64_encode(stream_get_contents($hous->getPicture1()));
        }
    }

    return $this->render('GuestHouseBundle:Home:addDateV.html.twig', array('add'=>$addT,'picture1'=>$images));

}
    public function researchPriceAction ($key1){

        $em = $this->getDoctrine()->getManager();
        $date=$em->getRepository('AppBundle:Date')->findAll();

        $add1=array();
        foreach ($date as $d){
            $h=$d->getFkAddid();
            array_push($add1,$h);
        }

        $addP=array();
        $x=array();
        foreach ($add1 as $c){
            if ($c->getPrice()<= $key1){
                array_push($addP ,$c);


            }
        }

        arsort($x);

        $images=array();
        foreach ($addP as $hous) {
            if ($hous->getPicture1() != null) {
                $images[$hous->getId()] = base64_encode(stream_get_contents($hous->getPicture1()));
            }
        }

        return $this->render('GuestHouseBundle:Home:addDateV.html.twig', array('add'=>$addP,'picture1'=>$images));

    }
    public function researchAirAction ($key2){

        $em = $this->getDoctrine()->getManager();
        $date=$em->getRepository('AppBundle:Date')->findAll();

        $add1=array();
        foreach ($date as $d){
            $h=$d->getFkAddid();
            array_push($add1,$h);
        }

        $addA=array();
        $x=array();
        foreach ($add1 as $c){
            if ($c->isAirconditioner()== true){
                array_push($addA ,$c);


            }
        }

        arsort($x);

        $images=array();
        foreach ($addA as $hous) {
            if ($hous->getPicture1() != null) {
                $images[$hous->getId()] = base64_encode(stream_get_contents($hous->getPicture1()));
            }
        }

        return $this->render('GuestHouseBundle:Home:addDateV.html.twig', array('add'=>$addA,'picture1'=>$images));

    }
    public function researchFireAction ($key3){

        $em = $this->getDoctrine()->getManager();
        $date=$em->getRepository('AppBundle:Date')->findAll();

        $add1=array();
        foreach ($date as $d){
            $h=$d->getFkAddid();
            array_push($add1,$h);
        }

        $addA=array();
        $x=array();
        foreach ($add1 as $c){
            if ($c->isFireplace()== true){
                array_push($addA ,$c);


            }
        }

        arsort($x);

        $images=array();
        foreach ($addA as $hous) {
            if ($hous->getPicture1() != null) {
                $images[$hous->getId()] = base64_encode(stream_get_contents($hous->getPicture1()));
            }
        }

        return $this->render('GuestHouseBundle:Home:addDateV.html.twig', array('add'=>$addA,'picture1'=>$images));

    }
    public function researchWashAction ($key3){

        $em = $this->getDoctrine()->getManager();
        $date=$em->getRepository('AppBundle:Date')->findAll();

        $add1=array();
        foreach ($date as $d){
            $h=$d->getFkAddid();
            array_push($add1,$h);
        }

        $addA=array();
        $x=array();
        foreach ($add1 as $c){
            if ($c->isWashingmachine()== true){
                array_push($addA ,$c);


            }
        }

        arsort($x);

        $images=array();
        foreach ($addA as $hous) {
            if ($hous->getPicture1() != null) {
                $images[$hous->getId()] = base64_encode(stream_get_contents($hous->getPicture1()));
            }
        }

        return $this->render('GuestHouseBundle:Home:addDateV.html.twig', array('add'=>$addA,'picture1'=>$images));

    }
    public function researchTvAction ($key3){

        $em = $this->getDoctrine()->getManager();
        $date=$em->getRepository('AppBundle:Date')->findAll();

        $add1=array();
        foreach ($date as $d){
            $h=$d->getFkAddid();
            array_push($add1,$h);
        }

        $addA=array();
        $x=array();
        foreach ($add1 as $c){
            if ($c->isTv()== true){
                array_push($addA ,$c);


            }
        }

        arsort($x);

        $images=array();
        foreach ($addA as $hous) {
            if ($hous->getPicture1() != null) {
                $images[$hous->getId()] = base64_encode(stream_get_contents($hous->getPicture1()));
            }
        }

        return $this->render('GuestHouseBundle:Home:addDateV.html.twig', array('add'=>$addA,'picture1'=>$images));

    }
}
