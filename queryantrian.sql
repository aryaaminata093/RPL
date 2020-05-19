SELECT noantrian, tglantrian, id_pasien, namapasien, id_jadwal, namadokter
FROM rumahsakit.pasien JOIN rumahsakit.antrian ON idpasien=id_pasien
JOIN rumahsakit.jadwal ON id_jadwal=idjadwal
JOIN rumahsakit.dokter ON id_dokter=iddokter;

#insert data selain noantrian
INSERT INTO rumahsakit.antrian (tglantrian, id_pasien, id_jadwal) 
VALUES ('2020-02-20', '11', '31');

#isi noantrian
UPDATE rumahsakit.antrian
SET noantrian = IF((SELECT count(id_pasien) FROM rumahsakit.antrian WHERE id_jadwal='31' AND tglantrian='2020-02-20')
='0','1',(SELECT count(id_pasien) FROM rumahsakit.antrian WHERE id_jadwal='31' AND tglantrian='2020-02-20'))
WHERE id_pasien = '11';

#next antrian
UPDATE rumahsakit.antrian
SET statusantrian = '1'
WHERE id_jadwal='$idjadwal' AND tglantrian='$tanggal' 
AND noantrian=(SELECT min(noantrian) FROM rumahsakit.antrian WHERE statusantrian='0')
