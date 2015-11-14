<?php
$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");


if($_POST)
{
    $q = pg_escape_string($_POST['search']);
    $strSQL_Result = pg_query("select isbn, title from books where title like '$q%' LIMIT 1");
    while($row=pg_fetch_array($strSQL_Result))
    {
        $title   = $row['title'];
	$isbn      = $row['isbn'];

      //  $email      = $row['email'];
       // $b_username = '<strong>'.$q.'</strong>';
        //$b_email    = '<strong>'.$q.'</strong>';
        //$final_username = str_ireplace($q, $b_username, $username);
        // $final_email = str_ireplace($q, $b_email, $email);
        ?>
            <div class="show" align="left">
            <span class="name"><a href = "book_main_page.php?isbn=<?php echo $isbn; ?> "> <?php echo $title; ?> </a></span>&nbsp;           </div>
        <?php
    }

}
?>
