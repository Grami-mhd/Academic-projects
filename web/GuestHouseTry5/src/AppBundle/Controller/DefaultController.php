<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class DefaultController extends Controller
{
    /**
     * @Route("/", name="homepage")
     */
    public function indexAction(Request $request)
    {
        // replace this example code with whatever you need
        $u=$this->getUser();
        if($u!=null)
            if($u->hasRole('ROLE_ADMIN')){
                return $this->redirectToRoute('admin_homepage');
            }
            else
                return $this->redirectToRoute('guest_house_home');
        else
            return $this->redirectToRoute("login");
    }
}
