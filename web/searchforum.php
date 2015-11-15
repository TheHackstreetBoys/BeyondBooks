<?php

include_once "db_conn.php";
if($_POST)
{
    $q = pg_escape_string($_POST['search']);
    $strSQL_Result = pg_query("select qid,title, content from question_forum where title like '$q%' or content like '$q%'  LIMIT 3");

    while($row=pg_fetch_array($strSQL_Result))
    {
        $title  = $row['title'];
	$content = $row['content'];
	$qid = $row['qid'];


        ?>
            <div class="show" align="left">
            <span class="name"><a href = "forumview.php?qid=<?php echo $qid ?> "> <?php echo $title; ?> </a></span>&nbsp;           </div>
        <?php
    }

}
?>
