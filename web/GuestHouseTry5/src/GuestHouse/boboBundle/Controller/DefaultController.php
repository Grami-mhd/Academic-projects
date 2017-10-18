<?php

namespace GuestHouse\boboBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('GuestHouseboboBundle:Default:index.html.twig');
    }
}
