<?php
	$host       =   'localhost';
	$user       =   'root';
	$password   =   '';
	$database   =   'rumahsakit';
	$konek = mysqli_connect($host, $user, $password, $database);
	if (!$konek) {
		die ('Gagal terhubung dengan MySQL: ' . mysqli_connect_error());
	}
?>