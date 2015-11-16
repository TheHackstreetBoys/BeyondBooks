<?php
include_once "db_conn.php";

if($_POST)
{
    $q = pg_escape_string($_POST['search']);
    $strSQL_Result = pg_query("select isbn, title from books where title like '$q%' or isbn like '$q%' LIMIT 1");
    $strSQL_Result = pg_query("select books.isbn, books.title, author.author from books join author on author.isbn = books.isbn where books.title like '$q%' or books.isbn like '$q%' or author.author like '$q%' ");


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
