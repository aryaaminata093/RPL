#menampilkan tabel di web
SELECT noantrian, tglantrian, id_pasien, namapasien, id_jadwal, namadokter
FROM rumahsakit.pasien JOIN rumahsakit.antrian ON idpasien=id_pasien
JOIN rumahsakit.jadwal ON id_jadwal=idjadwal
JOIN rumahsakit.dokter ON id_dokter=iddokter;

#declare variabel
SET @c := '0'; 

#insert data selain noantrian
INSERT INTO rumahsakit.antrian (tglantrian, id_pasien, id_jadwal) 
VALUES ('2020-02-20', '1', '22');

#hitung banyak antrian yg sudah ada
SELECT @c := count(id_pasien) FROM rumahsakit.antrian WHERE id_jadwal='22' AND tglantrian='2020-02-20';

#isi noantrian
UPDATE rumahsakit.antrian
SET noantrian = IF(@c='0','1',@c)
WHERE id_pasien = '1';

#next antrian - delete
DELETE FROM rumahsakit.antrian
WHERE id_jadwal='32' AND tglantrian='2020-02-20'
ORDER BY noantrian
LIMIT 1;
