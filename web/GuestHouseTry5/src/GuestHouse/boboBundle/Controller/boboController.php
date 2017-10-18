<?php

namespace GuestHouse\boboBundle\Controller;

use AppBundle\Entity\Date;
use GuestHouse\boboBundle\Form\addasOwner;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class boboController extends Controller
{
    public function indexAction()
    {
        return $this->render('GuestHouseboboBundle:home:home.html.twig', array());
    }

    public function addAction(){

        return $this->render('GuestHouseboboBundle:add:modify.html.twig', array());
    }

    public function affichageAction($ide, Request $request){

        $em = $this->getDoctrine()->getManager();
        $house = $em->getRepository("AppBundle:House")->find($ide);
        $houses= $em->getRepository("AppBundle:House")->findAll();
        $dat = $em->getRepository("AppBundle:Date")->findBy(array('fkAddid' => $ide));

        /*$query = $em->createQuery(
            'SELECT d
             FROM AppBundle:Date d
              WHERE d.fkAddid = :ide'
        )->setParameter('fkAddid',$ide);
        $date = $query->getResult();*/



        $comment=  $em->getRepository("AppBundle:Comment")->findBy(array('fkCommentHouseid' => $ide));


        /* $query = $em->createQuery(
             'SELECT c
             FROM AppBundle:Comment c
             WHERE c.fkCommentHouseid = :ide
             ')->setParameter('ide', $ide);

         $comment = $query->getResult();*/


        $commenthouse  = $this->get('knp_paginator')->paginate(
            $comment,
            $request->query->getInt('page',1),3

        );

        $images=array();
        foreach ($houses as $key => $hous){
            if($hous->getPicture1() != null)
                $images[$hous->getId()]=base64_encode(stream_get_contents($hous->getPicture1()));
        }
        $images1=array();
        foreach ($houses as $key => $hous){
            if($hous->getPicture2() != null)
                $images1[$hous->getId()]=base64_encode(stream_get_contents($hous->getPicture2()));
        }
        return $this->render('GuestHouseboboBundle:add:add.html.twig', array('add' => $house ,'houses' => $houses ,'comment' => $commenthouse , 'pictures' =>$images ,'pictures1' =>$images1 ,
            'dat'=>$dat));
    }

    public function AjoutAction(Request $request,$ide)
    {
        $em = $this->getDoctrine()->getManager();
        $house = $em->getRepository("AppBundle:House")->find($ide);
        $houses= $em->getRepository("AppBundle:House")->findAll();

        $comment=  $em->getRepository("AppBundle:Comment")->findBy(array('fkCommentHouseid' => $ide));

        $commenthouse  = $this->get('knp_paginator')->paginate(
            $comment,
            $request->query->getInt('page',1),3

        );

        $images=array();
        foreach ($houses as $key => $hous){
            if($hous->getPicture1() != null)
                $images[$hous->getId()]=base64_encode(stream_get_contents($hous->getPicture1()));
        }
        $images1=array();
        foreach ($houses as $key => $hous){
            if($hous->getPicture2() != null)
                $images1[$hous->getId()]=base64_encode(stream_get_contents($hous->getPicture2()));
        }


            if ($request->isMethod('POST')) {
                $date = new Date();
                $date->setDate($request->get('novodate'));
                $date->setFkAddid($house);
                $em = $this->getDoctrine()->getManager();
                $em->persist($date);
                $em->flush();
            }

        $dat = $em->getRepository("AppBundle:Date")->findBy(array('fkAddid' => $ide));

        /*$Form1 = $this->createForm(addasOwner::class, $house);

        if ($request->isMethod('POST')) {
            $Form1->handleRequest($request);

            if ($Form1->isSubmitted()) {
                $house1 -> setNature($request->get('nature'));
                $house1 -> setPrice($request->get('prix'));
                $house1 -> setDescription($request->get('text'));

                $em->persist($house1);
                $em->flush();
            }
        }*/

         /*   if( $request -> isMethod('POST')){
                $house -> setNature($request->get('nature'));
                $house -> setPrice($request->get('prix'));
                $house -> setDescription($request->get('text'));

                $em->persist($house);
                $em->flush();
            }*/




        return $this->render('GuestHouseboboBundle:add:addasOwner.html.twig', array('add' => $house ,'houses' => $houses ,'comment' => $commenthouse , 'pictures' =>$images ,'pictures1' =>$images1 ,
            'dat'=>$dat));
    }

    public function updateAction(Request $request,$ide)
    {
        $em = $this->getDoctrine()->getManager();
        $house = $em->getRepository('AppBundle:House')->find($ide);
        $houses= $em->getRepository("AppBundle:House")->findAll();

        $images=array();
        foreach ($houses as $key => $hous){
            if($hous->getPicture1() != null)
                $images[$hous->getId()]=base64_encode(stream_get_contents($hous->getPicture1()));
        }
        $images1=array();
        foreach ($houses as $key => $hous){
            if($hous->getPicture2() != null)
                $images1[$hous->getId()]=base64_encode(stream_get_contents($hous->getPicture2()));
        }



        if( $request -> isMethod('POST')){
                        $house -> setNature($request->get('nature'));
                        $house -> setPrice($request->get('prix'));
                        $house -> setDescription($request->get('text'));

                        $em->persist($house);
                        $em->flush();
                    }
        return $this->render('GuestHouseboboBundle:add:modify.html.twig', array( 'add' => $house , 'houses' => $houses , 'pictures' =>$images  ,'pictures1' =>$images1 ));
    }

  /* public function ajoutAction(Request $request){



        /*$Form = $this->createForm(addasOwnerForm::class, $date);
        //$date->setFkAddid($ide);
        $Form->handleRequest($request);
        if ($Form->isValid()) {

            $em = $this->getDoctrine()->getManager();
            $em->persist($date); //insert into
            $em->flush(); //synchroniser entre bd et la mémoire
            return $this->redirectToRoute('guest_housebobo_test1');
        }
        return $this->render('GuestHouseboboBundle:add:addasOwner.html.twig', array('forme' => $Form->createView()));
    }*/

       /* if ($request->isMethod('GET')) {
            $date = new Date();
            $date->setDate();
            $date->setFkAddid($request->get('ide'));
            $em = $this->getDoctrine()->getManager();
            $em->persist($date);
            $em->flush();
        }



        return $this->redirectToRoute('guest_housebobo_test1',array('ide'=> $ide));


    }*/

    public function affichagetestAction($ide, Request $request){

        $em = $this->getDoctrine()->getManager();
        $house = $em->getRepository("AppBundle:House")->find($ide);
        $houses= $em->getRepository("AppBundle:House")->findAll();
        $dat = $em->getRepository("AppBundle:Date")->findBy(array('fkAddid' => $ide));

        /*$query = $em->createQuery(
            'SELECT d
             FROM AppBundle:Date d
              WHERE d.fkAddid = :ide'
        )->setParameter('fkAddid',$ide);
        $date = $query->getResult();*/

        $comment=  $em->getRepository("AppBundle:Comment")->findBy(array('fkCommentHouseid' => $ide));


       /* $query = $em->createQuery(
            'SELECT c
            FROM AppBundle:Comment c
            WHERE c.fkCommentHouseid = :ide
            ')->setParameter('ide', $ide);

        $comment = $query->getResult();*/


        $commenthouse  = $this->get('knp_paginator')->paginate(
            $comment,
            $request->query->getInt('page',1),3

        );

        $images=array();
        foreach ($houses as $key => $hous){
            if($hous->getPicture1() != null)
                $images[$hous->getId()]=base64_encode(stream_get_contents($hous->getPicture1()));
        }
        $images1=array();
        foreach ($houses as $key => $hous){
            if($hous->getPicture2() != null)
                $images1[$hous->getId()]=base64_encode(stream_get_contents($hous->getPicture2()));
        }
        return $this->render('GuestHouseboboBundle:add:add.html.twig', array('add' => $house ,'houses' => $houses ,'comment' => $commenthouse , 'pictures' =>$images ,'pictures1' =>$images1 ,
            'dat'=>$dat));
    }
}
