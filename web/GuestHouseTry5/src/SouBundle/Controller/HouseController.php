<?php

namespace SouBundle\Controller;

use AppBundle\Entity\House;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class HouseController extends Controller
{
    public function indexAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $u=$this->getUser();
        $image= null;
        if($u->getPicture() != null)
            $image = base64_encode(stream_get_contents( $u->getPicture()));

        $images = array();

        $house=new House();

        if( $request->isMethod("POST")){

            $house -> setCountry($request->get('country'));
            $house -> setTown($request->get('town'));
            $house -> setNature($request->get('nature'));
            $house -> setPrice($request->get('price'));
            //$house -> setPicture1(basename(stream_get_contents($request->get('pic'))));

            $house->setFkUser($u);
            $em->persist($house);
            $em->flush();
        }
        $houses=$em->getRepository('AppBundle:House')->findBy(array('fkUser'=>$u->getId()));
        foreach ($houses as $hous){
            if($hous->getPicture1()!= null){
            $images[$hous->getId()]=base64_encode(stream_get_contents($hous->getPicture1()));
            }
        }
        return $this->render('SouBundle:house:House.html.twig',array('houses'=>$houses,'user'=>$u,'picture'=>$image,'picture1'=>$images));
    }

    public function AjoutAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $u = $this->getUser();

        $house=new House();

        if( $request->isMethod("POST")){

            $house -> setCouhntry($request->get('country'));
            $house -> setTown($request->get('town'));
            $house -> setNature($request->get('nature'));
            $house -> setPrice($request->get('price'));
            $house->setFkUser($u);
            $em->persist($house);
            $em->flush();
        }

        $houses=$em->getRepository('AppBundle:House')->findBy(array('fkUser'=>$u->getId()));
        return $this->render('SouBundle:house:House.html.twig',array('houses'=>$houses,'user'=>$u));

    }



}
