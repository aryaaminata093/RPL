<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	require_once "koneksi.php";
	$email = $_POST["email"];
	$password = $_POST["password"];

	//$password = password_hash($password, PASSWORD_DEFAULT);

	$query = "SELECT * FROM datapasien WHERE psn_email = '$email';";
	$sql = mysqli_query($konek, $query);
	

	if( mysqli_num_rows($sql) > 0 ){
		while ($row = mysqli_fetch_assoc($sql)){
			
			if(password_verify($password, $row["psn_password"])){
				$result = array(
					"success" => 1,
					"message" => "berhasil",
					"id" => $row['psn_id'],
					"email" => $row['psn_email'],
					"nama" => $row["psn_nama"],
					"tmptlhr" => $row["tmpt_lahir"],
					"tgllhr" => $row["tgl_lahir"],
					"alamat" => $row["alamat"],
					"jk" => $row["psn_jk"],
					"goldar" => $row["gol_darah"]
				);
			}
			else{
				$result = array("success"=> 2,"message"=> "salah password");
			}
		}


		echo json_encode($result);
		
	} else {
		
		$result = array("success"=> 404,"message"=> "email gk ada");
		echo json_encode($result);
	
	} 
	}else{

		$result = array( "success"=> 0,"message"=> "error post");
		echo json_encode($result);
	}
?>