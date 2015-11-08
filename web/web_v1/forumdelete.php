<?php
include 'mysql.php';

//session_start();
  //  $username   = $_SESSION['current'];
    //$mycourse = $_SESSION['mycourse']; 
    
mysql_safe_query('DELETE FROM posts WHERE id=%s LIMIT 1', $_GET['id']);
mysql_safe_query('DELETE FROM comments WHERE post_id=%s', $_GET['id']);
redirect('forumWelcome.php');

