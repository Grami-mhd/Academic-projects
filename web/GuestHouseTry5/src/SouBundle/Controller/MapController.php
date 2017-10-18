<?php

namespace SouBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class MapController extends Controller
{
    public function indexAction()
    {
        return $this->render('SouBundle:house:map.html.twig');
    }
}
