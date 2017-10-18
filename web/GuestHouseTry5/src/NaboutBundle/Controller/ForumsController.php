<?php

namespace NaboutBundle\Controller;

use AppBundle\Entity\Forum;
use AppBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class ForumsController extends Controller
{
    public function forumsAction(Request $request)
    {   $u = $this->getUser();
        $em = $this->getDoctrine()->getManager();
        $myforums = $em->getRepository('AppBundle:Forum')->findBy(array('fkForumOwnerid' => $u->getId()));

        $allforums = $em->getRepository('AppBundle:Forum')->findAll();


        if($request->isMethod('POST')){

            $f=new Forum();
            $f->setSubject($request->get('sujet'));
            $f->setFkForumOwnerid($u);


            $em->persist($f);
            $em->flush();
        }

        /****************************************oldDiscussions**********************************************/


        $mydiscuss = $em->getRepository('AppBundle:Message')->findBy(array('fkMessageOwnerid' => $u->getId()));
        $nbr2 = array();
        $oldDisc = array();
        $myOldDis = array();

        foreach ($mydiscuss as $m) {
            $f = $m->getFkMessageForumid();
            if ($f->isIscreated()) {
                if ($f->getFkForumOwnerid() == $u) {
                    array_push($myOldDis, $f);
                }

                if (!in_array($f, $oldDisc)) {
                    array_push($oldDisc, $m->getFkMessageForumid());
                }
            }

        }
        foreach ($oldDisc as $f) {
            $nbr2[$f->getId()] = count($em->getRepository('AppBundle:Message')->findBy(array('fkMessageForumid' => $f->getId())));
        }

        /****************************************************************************************************/


        $myCreatedForums = array();
        $allCreatedForums = array();

        $nbrMsg = array();
        $nbr1 = array();
        $pop = array();

        foreach ($myforums as $f) {

            if ($f->isIscreated()) {

                array_push($myCreatedForums, $f);
                $nbrMsg[$f->getId()] = count($em->getRepository('AppBundle:Message')->findBy(array('fkMessageForumid' => $f->getId())));

            }
        }

        foreach ($allforums as $f) {
            if ($f->isIscreated()) {
                array_push($allCreatedForums, $f);
                $nbr1[$f->getId()] = count($em->getRepository('AppBundle:Message')->findBy(array('fkMessageForumid' => $f->getId())));
            }

        }
        arsort($nbr1);

        foreach ($nbr1 as $k => $n) {
            $pop[$n] = $em->getRepository('AppBundle:Forum')->find($k);
        }

            return $this->render('NaboutBundle:Forums:viewForums.html.twig', array('pop' => $pop, 'forum' => $myCreatedForums, 'myoldDisc' => $myOldDis, 'nbrmsg' => $nbr2, 'oldDisc' => $oldDisc, 'nbr' => $nbrMsg));

    }

    public function researchforumsAction($key)
    {

        $em = $this->getDoctrine()->getManager();
        $forums = $em->getRepository('AppBundle:Forum')->findforumlike($key);

        $createdsearchforums = array();
        $nb = array();
        foreach ($forums as $f) {
            if ($f->isIscreated()) {
                array_push($createdsearchforums, $f);
                $nb[$f->getId()] = count($em->getRepository('AppBundle:Message')->findBy(array('fkMessageForumid' => $f->getId())));
            }
        }

        return $this->render('NaboutBundle:Forums:search.html.twig', array('nb' => $nb, 'forums' => $createdsearchforums));
    }

}