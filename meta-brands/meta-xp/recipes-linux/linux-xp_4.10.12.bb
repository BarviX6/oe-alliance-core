SUMMARY = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPLv2"

KERNEL_RELEASE = "4.10.12"

inherit kernel machine_kernel_pr

SRC_URI[md5sum] = "3c42df14db9d12041802f4c8fec88e17"
SRC_URI[sha256sum] = "738896d2682211d2079eeaa1c7b8bdd0fe75eb90cd12dff2fc5aeb3cc02562bc"

LIC_FILES_CHKSUM = "file://${WORKDIR}/linux-${PV}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

MACHINE_KERNEL_PR_prepend = "1"

# By default, kernel.bbclass modifies package names to allow multiple kernels
# to be installed in parallel. We revert this change and rprovide the versioned
# package names instead, to allow only one kernel to be installed.
PKG_${KERNEL_PACKAGE_NAME}-base = "kernel-base"
PKG_${KERNEL_PACKAGE_NAME}-image = "kernel-image"
RPROVIDES_${KERNEL_PACKAGE_NAME}-base = "kernel-${KERNEL_VERSION}"
RPROVIDES_${KERNEL_PACKAGE_NAME}-image = "kernel-image-${KERNEL_VERSION}"

SRC_URI += "http://source.mynonpublic.com/xp1000/linux-${PV}-${ARCH}.tar.gz \
    file://defconfig \
    file://export_pmpoweroffprepare.patch \
    file://TBS-fixes-for-4.10-kernel.patch \
    file://0001-Support-TBS-USB-drivers-for-4.6-kernel.patch \
    file://0001-TBS-fixes-for-4.6-kernel.patch \
    file://0001-STV-Add-PLS-support.patch \
    file://0001-STV-Add-SNR-Signal-report-parameters.patch \
    file://blindscan2.patch \
    file://0001-stv090x-optimized-TS-sync-control.patch \
    file://t230c2.patch \
    file://add-more-devices-rtl8xxxu.patch \
    file://move-default-dialect-to-SMB3.patch \
    file://0005-xbox-one-tuner-4.10.patch \
    file://0006-dvb-media-tda18250-support-for-new-silicon-tuner.patch \
    "

S = "${WORKDIR}/linux-${PV}"
B = "${WORKDIR}/build"

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_IMAGEDEST = "tmp"

KERNEL_OUTPUT = "vmlinux.gz"
KERNEL_OUTPUT_DIR = "."
KERNEL_IMAGETYPE = "vmlinux.gz"

KERNEL_EXTRA_ARGS = "EXTRA_CFLAGS=-Wno-attribute-alias"

pkg_postinst_kernel-image () {
	if [ "x$D" == "x" ]; then
		if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE} ] ; then
			flash_erase /dev/${MTD_KERNEL} 0 0
			nandwrite -p /dev/${MTD_KERNEL} /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}
		fi
	fi
	true
}

do_rm_work() {
}
