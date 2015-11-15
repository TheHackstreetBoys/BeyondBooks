<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <style type="text/css">

    </style>
  </head>
  <body>
      <p>

        <div style="float:left;width:90px">

</div>
<div style="float:left;width:120px;margin-left:20px;margin-top:2px">

</div>
<!-- Place this tag where you want the badge to render. -->
<div style="float: left; margin-left: -230px; margin-top: 34px; padding-right: 20px;">
<!-- Place this tag where you want the badge to render. -->


<!-- Place this tag after the last badge tag. -->
<script type="text/javascript">
  (function() {
    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
    po.src = 'https://apis.google.com/js/plusone.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
  })();
</script></div>
      </p>

      <hr />

    <p>
        <?php if(!isset($pre)){ ?>
      <pre>
        <?php print_r($content); ?>
      </pre>
      <?php } else{ ?>
       <?php print_r($content); ?>
      <?php } ?>
    </p>

  </body>
</html>
