<?php

namespace SouBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('SouBundle:Default:index.html.twig');
    }
}
