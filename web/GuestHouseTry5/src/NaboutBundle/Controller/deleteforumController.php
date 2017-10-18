<?php

namespace NaboutBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class deleteforumController extends Controller
{
    public function deleteforumAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $f=$em->getRepository('AppBundle:Forum')->find($id);
        $em->remove($f);
        $em->flush();

        $u = $this->getUser();
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
        $myforums = $em->getRepository('AppBundle:Forum')->findBy(array('fkForumOwnerid' => $u->getId()));

        $allforums = $em->getRepository('AppBundle:Forum')->findAll();

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

        return $this->render('NaboutBundle:Forums:myforums.html.twig', array('pop' => $pop, 'forum' => $myCreatedForums, 'myoldDisc' => $myOldDis, 'nbrmsg' => $nbr2, 'oldDisc' => $oldDisc, 'nbr' => $nbrMsg));
    }
}
