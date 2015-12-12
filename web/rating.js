		$(document).ready(function(){
			$('#rating_panel>img').click(function(e){
				var imgindex	=	$(this).index() + 1;
				var ratingpanel	=	$(this).closest('div');
				var pollid		=	ratingpanel.attr('data-pollid');
				var israted		=	ratingpanel.attr('data-rated');
				if(israted == 1){
					alert('You have already voted!');
					return false;
				}else{
					ratingpanel.attr('data-rated',1);
				}

				$('#starloader').show();
				for( i=0;i<imgindex; i++){
					var imgobj = $("#rating_panel>img:eq( "+i+" )" );
					var img = 'images/full.png';					
					imgobj.attr('src',img);
					
				}
				$.ajax({
					url:'add_rating.php',
					data:'rated='+imgindex+'&pid='+pollid,
					success:function(){
						$('#starloader').hide();
					}
				});
				
				
								
			});
		});
