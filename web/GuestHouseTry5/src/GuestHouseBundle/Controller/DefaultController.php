<?php

namespace GuestHouseBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;

class DefaultController extends Controller
{
    public function indexAction()
    {
        $u=$this->getUser();
        if($u!=null)
        if($u->hasRole('ROLE_ADMIN')){
            return $this->redirectToRoute('admin_homepage');
        }
        else
            return $this->redirectToRoute('guest_house_home');
        else
            return new Response("login");
    }
}
