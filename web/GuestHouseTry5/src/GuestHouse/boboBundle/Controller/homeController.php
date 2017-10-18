<?php

namespace GuestHouse\boboBundle\Controller;

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
        if($u->getPicture() != null)
            $image = base64_encode(stream_get_contents( $u->getPicture()));

        $houses=$em->getRepository('AppBundle:House')->findAll();

        $dates=$em->getRepository('AppBundle:Date')->findAll();

        $add=array();
        foreach ($dates as $date){
            $h=$date->getFkAddid();
            array_push($add,$h);
        }
        $images=array();
        foreach ($add as $hous) {
            if ($hous->getPicture1() != null) {
                $images[$hous->getId()] = base64_encode(stream_get_contents($hous->getPicture1()));
            }
        }
        return $this->render('GuestHouseboboBundle:Home:home.html.twig', array('houses' => $houses, 'user' => $u, 'picture' => $image, 'picture1' => $images,'add'=>$add));
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
        return $this->render('GuestHouseboboBundle:Home:addDateV.html.twig', array('add'=>$addV,'picture1'=>$images));
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

        return $this->render('GuestHouseboboBundle:Home:addDateV.html.twig', array('add'=>$addT,'picture1'=>$images));

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

        foreach ($add1 as $c){
            if ($c->getPrice()<= $key1){
                array_push($addP ,$c);
                array_push($x,$c->getPrice());

            }
        }

        $images=array();
        foreach ($addP as $hous) {
            if ($hous->getPicture1() != null) {
                $images[$hous->getId()] = base64_encode(stream_get_contents($hous->getPicture1()));
            }
        }

        return $this->render('GuestHouseboboBundle:Home:addDateV.html.twig', array('add'=>$addP,'picture1'=>$images));

    }
}
