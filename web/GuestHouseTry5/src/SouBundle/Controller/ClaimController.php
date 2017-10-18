<?php

namespace SouBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use AppBundle\Entity\Claim;


class ClaimController extends Controller
{
    public function indexAction()
    {
        $u=$this->getUser();
        $em = $this->getDoctrine()->getManager();
        $claims=$em->getRepository('AppBundle:Claim')->findBy(array('fkClaimOwnerrid'=>$u->getId()));

        return $this->render('SouBundle:claim:Claim.html.twig',array('user'=>$u,'cla'=>$claims));
    }
}
