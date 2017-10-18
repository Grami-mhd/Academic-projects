<?php

namespace AdminBundle\Controller;

use Ob\HighchartsBundle\Highcharts\Highchart;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Zend\Json\Expr;

class DefaultController extends Controller
{
    public function indexAction()
    {
        $em=$this->getDoctrine()->getManager();
        $houses=$em->getRepository('AdminBundle:House')->findAll();

        $stars=array();
        $rates=array();
        if(count($houses)!=0)
            for ($i=1;$i<6;$i++) {
                array_push($stars, $i . "s");
                array_push($rates, count($em->getRepository('AdminBundle:Rate')->findBy(array('fkRateHouseid' => $houses[0], 'rate' => $i))));
            }
        $series = array(
            array(
                'name'  => 'House',
                'type'  => 'column',
                'color' => '#A325A0',
                'yAxis' => 0,
                'data'  => $rates,
            )
        );
        $yData = array(

            array(
                'labels' => array(
                    'formatter' => new Expr('function () { return this.value + "" }'),
                    'style'     => array('color' => '#A325A0')
                ),
                'gridLineWidth' => 0,
                'title' => array(
                    'text'  => 'Number of rates',
                    'style' => array('color' => '#A325A0')
                ),
            ),
        );


    $ob = new Highchart();
    $ob->chart->renderTo('chartcont'); // The #id of the div where to render the chart
    $ob->chart->type('column');
    $ob->title->text('number of rates per house');

    $ob->xAxis->categories($stars);

    $ob->yAxis($yData);

    $ob->legend->enabled(false);
    $formatter = new Expr('function () {
        var unit = {
                     "Etudiant": "étudiant(s)",
                     
                 }[this.series.name];
                 return this.x + ": <b>" + this.y + "</b> " + unit;
             }');
        $ob->tooltip->formatter($formatter);
        $ob->series($series);
        return $this->render('AdminBundle:Admin:home.html.twig',array('houses'=>$houses,'chart'=>$ob));
    }

    public function statsAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $house=$em->getRepository('AdminBundle:House')->find($id);


        $stars=array();
        $rates=array();

        for ($i=1;$i<6;$i++) {
            array_push($stars, $i . "s");
            array_push($rates, count($em->getRepository('AdminBundle:Rate')->findBy(array('fkRateHouseid' => $house, 'rate' => $i))));
        }
        $series = array(
            array(
                'name'  => 'House',
                'type'  => 'column',
                'color' => '#A325A0',
                'yAxis' => 0,
                'data'  => $rates,
            )
        );
        $yData = array(

            array(
                'labels' => array(
                    'formatter' => new Expr('function () { return this.value + "" }'),
                    'style'     => array('color' => '#A325A0')
                ),
                'gridLineWidth' => 0,
                'title' => array(
                    'text'  => 'Number of rates',
                    'style' => array('color' => '#A325A0')
                ),
            ),
        );

        $ob = new Highchart();
        $ob->chart->renderTo('chartcont'); // The #id of the div where to render the chart
        $ob->chart->type('column');
        $ob->title->text('number of rates per house');

        $ob->xAxis->categories($stars);

        $ob->yAxis($yData);

        $ob->legend->enabled(false);
        $formatter = new Expr('function () {
        var unit = {
                     "Etudiant": "étudiant(s)",
                     
                 }[this.series.name];
                 return this.x + ": <b>" + this.y + "</b> " + unit;
             }');
        $ob->tooltip->formatter($formatter);
        $ob->series($series);





        return $this->render('AdminBundle:stats:stat.html.twig',array('chart'=>$ob));

    }
    public  function usersAction()
    {
        $em=$this->getDoctrine()->getManager();
        $users=$em->getRepository('AppBundle:User')->findAll();
        $images =array();
        foreach ($users as $key => $user){
            if($user->getPicture() != null)
           $images[$user->getId()] = base64_encode(stream_get_contents( $user->getPicture()));
        }
        return $this->render('AdminBundle:Admin:users.html.twig',array('users'=>$users, 'pictures'=>$images));
    }

    public  function profileAction(Request $request)
    {
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
        return $this->render('AdminBundle:Admin:profile.html.twig',array('user'=>$u,'picture'=>$image));
    }

    public  function mapAction()
    {
        $hs=$this->getDoctrine()->getManager()->getRepository('AdminBundle:House')->findAll();
        return $this->render('AdminBundle:Admin:map.html.twig',array('houses'=>$hs));
    }
    public function rforumsAction()
    {
        $em =$this->getDoctrine()->getManager();
        $allforums=$em->getRepository('AppBundle:Forum')->findAll();
        $forums = array();
        foreach ($allforums as $forum){
            if(count($forum->getFkForumReportUserid()) != 0  ){
                array_push($forums,$forum);
            }
        }

        $images =array();
        foreach ($forums as $key => $f){
            if(($f->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($f->getFkForumOwnerid()->getId(),$images)))
                $images[$f->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $f->getFkForumOwnerid()->getPicture()));
        }
        return $this->render('AdminBundle:Admin:rforums.html.twig',array('forums'=>$forums,'images'=>$images));
    }
    public  function demendsAction()
    {
        $em=$this->getDoctrine()->getManager();
        $forums=$em->getRepository('AppBundle:Forum')->findBy(array('iscreated' => "0"));
        $images =array();
        foreach ($forums as $key => $f){
            if(($f->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($f->getFkForumOwnerid()->getId(),$images)))
                $images[$f->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $f->getFkForumOwnerid()->getPicture()));
        }
        $other = array();
        $members=array();
        $numbers=array();
        foreach ($forums as $f){
            $other[$f->getFkForumOwnerid()->getId()]=$em->getRepository('AppBundle:Forum')->findBy(array('fkForumOwnerid' => $f->getFkForumOwnerid()->getId(),'iscreated'=>"1"));
            foreach ($other as $ofl){
                foreach ($ofl as $of){
                    $members[$of->getId()]=$em->getRepository('AppBundle:Message')->findBy(array('fkMessageForumid' =>$of->getId()));
                    $numbers[$of->getId()]=count($members[$of->getId()]);
                }
            }

        }

        return $this->render('AdminBundle:Admin:demends.html.twig',array('forums'=>$forums , 'images'=>$images,'other'=>$other , 'numbers'=>$numbers));
    }
    public  function rcommentsAction()
    {
        $em =$this->getDoctrine()->getManager();
        $allcomments=$em->getRepository('AdminBundle:Comment')->findAll();
        $comments = array();

        foreach ($allcomments as $comment){
            if(count($comment->getFkCommentReportOwnerid()) != 0  ){
                array_push($comments,$comment);
            }
        }

        $images =array();
        foreach ($comments as $c){
            if(($c->getFkCommentOwnerid()->getPicture() != null)&&(! array_key_exists($c->getFkCommentOwnerid()->getId(),$images)))
                $images[$c->getFkCommentOwnerid()->getId()] = base64_encode(stream_get_contents( $c->getFkCommentOwnerid()->getPicture()));
        }
        return $this->render('AdminBundle:Admin:rcomments.html.twig',array('comments'=>$comments,'images'=>$images));
    }
    public  function rmessagesAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $rmessages=$em->getRepository('AdminBundle:Messagereport')->findBy(array('istreated'=>"0"));
        $forums=array();
        $i=0;
        foreach ($rmessages as $m){
            if(!in_array($m->getFkMessage()->getFkMessageForumid(),$forums)){
                $forums[$i]=$m->getFkMessage()->getFkMessageForumid();
                $i++;
            }
        }
        $reported = array();
        foreach ($rmessages as $m){
            array_push($reported,$m->getFkMessage());
        }
        $images=array();
        if($i!=0){
            if($id<0) {
                if(($forums[0]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[0]->getFkForumOwnerid()->getId(),$images)))
                    $images[$forums[0]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[0]->getFkForumOwnerid()->getPicture()));
                $messages = $em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid' => $forums[0]->getId()));
                return $this->render('AdminBundle:Admin:rmessages.html.twig', array('pages' => $i, 'page' => 1, 'forum' => $forums[0],'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages,'images'=>$images));
            }if($id >= $i) {
                if(($forums[$i-1]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[$i-1]->getFkForumOwnerid()->getId(),$images)))
                    $images[$forums[$i-1]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[$i-1]->getFkForumOwnerid()->getPicture()));
                $messages =$em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid'=>$forums[$i-1]->getId()));

                return $this->render('AdminBundle:Admin:rmessages.html.twig', array('pages' => $i, 'page' => $i, 'forum' => $forums[$i - 1],'images'=>$images,'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages));
            }
            if(($forums[$id]->getFkForumOwnerid()->getPicture() != null)&&(! array_key_exists($forums[$id]->getFkForumOwnerid()->getId(),$images)))
                $images[$forums[$id]->getFkForumOwnerid()->getId()] = base64_encode(stream_get_contents( $forums[$id]->getFkForumOwnerid()->getPicture()));

            $messages =$em->getRepository('AdminBundle:Message')->findBy(array('fkMessageForumid'=>$forums[$id]->getId()));
            return $this->render('AdminBundle:Admin:rmessages.html.twig', array('pages' => $i, 'page' => $id + 1, 'forum' => $forums[$id],'images'=>$images,'messages'=>$messages,'reported'=>$reported,'msgreports'=>$rmessages));

        }
        else {
            return $this->render('AdminBundle:Admin:rmessages.html.twig', array('pages' => $i, 'page' => null, 'forum' => null,'messages'=>null,'images'=>null,'reported'=>null,'msgreports'=>null));
        }
    }
    public  function homeAction()
    {
        return new Response("home ya bro");
    }
    public  function claimsAction( Request $request)
    {
        $em=$this->getDoctrine()->getManager();

        $claims=$em->getRepository('AdminBundle:Claim')->findBy(array('istreated'=>"0"));
        if($claims != null) {
            $claim = $claims[0];
            $traveler = $claim->getFkClaimOwnerrid();
            $host = $claim->getFkClaimHouseid()->getFkUser();
        }
        if($request->isMethod('POST')){
            if($request->get('dtraveler')){
                $em->remove($traveler);
            }

            if($request->get('dhouse')){
                $em->remove($claim->getFkClaimHouseid());
                $claim->setIstreated(true);
                $claim->setStatus($claim->getStatus()." The house you claimed about has been deleted,");
                $em->persist($claim);
                $em->flush();
            }

            if($request->get('refund')){
                $traveler->setPoints($traveler->getPoints()+$request->get('refundpoints'));
                $host->setPoints($host->getPoints()-$request->get('refundpoints'));
                $em->persist($traveler);
                $em->persist($host);
                $claim->setIstreated(true);
                $claim->setStatus($claim->getStatus()." we refund you ".$request->get('refundpoints')." points,");
                $em->persist($claim);
                $em->flush();
            }
            if($request->get('dhost')){
                $em->remove($host);
                $claim->setIstreated(true);
                $claim->setStatus($claim->getStatus()." The User you claimed about has been deleted,");
                $em->persist($claim);
                $em->flush();
            }

            if(! $claim->isIstreated()){
                $claim->setIstreated(true);
                $claim->setStatus($claim->getStatus()."your claim has been refused");
                $em->persist($claim);
                $em->flush();
            }

            $claims=$em->getRepository('AdminBundle:Claim')->findBy(array('istreated'=>"0"));
            if($claims != null) {
                $claim = $claims[0];
                $traveler = $claim->getFkClaimOwnerrid();
                $host = $claim->getFkClaimHouseid()->getFkUser();
            }

        }

        if($claims != null){
            $pictures = array();
            if($host->getPicture() != null)
                $pictures[$host->getEmail()] = base64_encode(stream_get_contents( $host->getPicture()));
            if($traveler->getPicture() != null)
                $pictures[$traveler->getEmail()] = base64_encode(stream_get_contents( $traveler->getPicture()));
            return $this->render('AdminBundle:Admin:claims.html.twig',array('claim'=>$claim,'traveler'=>$traveler,'host'=>$host,'pictures'=>$pictures));
        }
        else
            return $this->render('AdminBundle:Admin:claims.html.twig',array('claim'=>null,'traveler'=>null,'host'=>null));
    }
}
