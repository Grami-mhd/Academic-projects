<?php

namespace SouBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;


class ProfileController extends Controller
{
    public function indexAction()
    {
        $u=$this->getUser();
        return $this->render('SouBundle:profile:Profile.html.twig',array('user'=>$u));
    }

    public function updateAction(Request $request){
        $em = $this -> getDoctrine() -> getManager();
        $u = $this->getUser();
        $image= null;
        if($u->getPicture() != null)
            $image = base64_encode(stream_get_contents( $u->getPicture()));
        $user = $em -> getRepository('AppBundle:User')->find($u->getId());
        if( $request -> isMethod('POST')){
            $user -> setUsername($request->get('username'));
            $user -> setFirstname($request->get('firstname'));
            $user -> setLastname($request->get('lastname'));
            $user -> setPhone($request->get('phone'));
            $user -> setTown($request->get('town'));
            $user -> setCountry($request->get('country'));
            $em->persist($user);
            $em->flush();
        }

        return $this->render("SouBundle:profile:Profile.html.twig",array('user'=>$u,'picture'=>$image));

    }
}
