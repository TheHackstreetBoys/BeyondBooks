<?php

include_once "db_conn.php";
if($_POST)
{
    $q = pg_escape_string($_POST['search']);
    $strSQL_Result = pg_query("select isbn, publisher, title from books where title like '$q%' LIMIT 3");

    while($row=pg_fetch_array($strSQL_Result))
    {
        $isbn  = $row['isbn'];
	$publisher    = $row['publisher'];
	$title        = $row['title'];

        ?>
            <div class="show" align="left">
            <span class="name"><a href = "book_main_page.php?isbn=<?php echo $isbn ?> ">  </a></span>&nbsp;           </div>
        <?php
    }

}
?>
