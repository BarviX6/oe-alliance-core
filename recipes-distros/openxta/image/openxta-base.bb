DESCRIPTION = "Base packages require for image."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PV = "1.0"
PR = "r10"

inherit packagegroup

RDEPENDS_${PN} = "\
	oe-alliance-base \
	openxta-enigma2 \
	openxta-bootlogo \
	libcrypto-compat \
	hddtemp \
	dosfstools \
	ntfs-3g \
	packagegroup-base-smbfs-client \
	packagegroup-base-smbfs \
	packagegroup-base-nfs \
	busybox-cron \
	unrar \
	ofgwrite \
	enigma2-plugin-drivers-dvb-usb-af9035 \
	"
