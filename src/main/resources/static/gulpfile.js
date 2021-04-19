const fs = require('fs');
const gulp = require('gulp');
const gulpif = require('gulp-if');
const concat = require('gulp-concat');
const header = require('gulp-header');
const cleanCSS = require('gulp-clean-css');
const sourcemaps = require('gulp-sourcemaps');
const uglifyes = require('uglify-es');
const uglifycss = require('gulp-uglifycss');
const rename = require('gulp-rename');
const maps = require('gulp-sourcemaps');
const composer = require('gulp-uglify/composer');
const uglify = composer(uglifyes, console);
const stripCssComments = require('gulp-strip-css-comments');

const argv = require('minimist')(process.argv.slice(2));

const config = {
    production: !!argv.production,
};

console.log('Config ', config);

function addBOM() {
    return header('\ufeff');
};

gulp.task('login-css', function() {
    return gulp.src([
        'node_modules/bootstrap/dist/css/bootstrap.min.css',
        'node_modules/@fortawesome/fontawesome-free/css/all.min.css',
        'node_modules/animate.css/animate.min.css',
        'css/reset.css',
        'css/index.css',
        'css/spinners.css',
        'css/style.css'])
        .pipe(gulpif(!config.production, sourcemaps.init()))
        .pipe(concat('login.css'))
        .pipe(gulpif(config.production, cleanCSS({ level: 2 })))
        .pipe(gulpif(config.production, stripCssComments({ preserve: false })))
        .pipe(addBOM())
        .pipe(gulpif(!config.production, sourcemaps.write('./')))
        .pipe(gulp.dest('dist/css/'));
});

gulp.task('app-css', function() {
    return gulp.src([
        'node_modules/bootstrap/dist/css/bootstrap.min.css',
        'node_modules/@fortawesome/fontawesome-free/css/all.min.css',
        'node_modules/metismenu/dist/metisMenu.min.css',
        'node_modules/jquery-toast-plugin/dist/jquery.toast.min.css',
        'node_modules/morris.js/morris.css',
        'node_modules/chartist/dist/chartist.min.css',
        'node_modules/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.css',
        'node_modules/animate.css/animate.min.css',
        'css/style.css',
        'css/default.css',
        'css/app.css'])
        .pipe(gulpif(!config.production, sourcemaps.init()))
        .pipe(concat('app.css'))
        .pipe(gulpif(config.production, cleanCSS({ level: 2 })))
        .pipe(gulpif(config.production, stripCssComments({ preserve: false })))
        .pipe(addBOM())
        .pipe(gulpif(!config.production, sourcemaps.write('./')))
        .pipe(gulp.dest('dist/css/'));
});

// minify css
gulp.task('copy-fonts', function() {
    return gulp.src([
        'node_modules/@fortawesome/fontawesome-free/webfonts/fa-solid-900.woff2',
        'node_modules/@fortawesome/fontawesome-free/webfonts/fa-solid-900.woff',
        'node_modules/@fortawesome/fontawesome-free/webfonts/fa-solid-900.ttf',
        'node_modules/@fortawesome/fontawesome-free/webfonts/fa-brands-400.woff2',
        'node_modules/@fortawesome/fontawesome-free/webfonts/fa-brands-400.woff',
        'node_modules/@fortawesome/fontawesome-free/webfonts/fa-brands-400.ttf'])
    .pipe(gulp.dest('dist/webfonts/'));
});

gulp.task('custom-js', function() {
    return gulp.src([
        'js/waves.js',
        'js/validation.js',
        'js/custom.js'])
    .pipe(concat('custom.js'))
    .pipe(gulpif(!config.production, uglify()))
    .pipe(addBOM())
    .pipe(gulp.dest('dist/js/'));
});

gulp.task('app-js', function() {
    fs.copyFileSync(
        'node_modules/knockout/build/output/knockout-latest.js',
        'node_modules/knockout/build/output/knockout.min.js'
    );

    fs.copyFileSync(
        'node_modules/knockout/build/output/knockout-latest.debug.js',
        'node_modules/knockout/build/output/knockout.js'
    );

    fs.copyFileSync(
        'node_modules/bootbox/src/bootbox.js',
        'node_modules/bootbox/dist/bootbox.js'
    );

    fs.copyFileSync(
        'node_modules/jquery-toast-plugin/src/jquery.toast.js',
        'node_modules/jquery-toast-plugin/dist/jquery.toast.js'
    );

    fs.copyFileSync(
        'dist/js/custom.js',
        'dist/js/custom.min.js'
    );

    var files = [
        'node_modules/jquery/dist/jquery.min.js',
        'node_modules/bootstrap/dist/js/bootstrap.min.js',
        'node_modules/knockout/build/output/knockout.min.js',
        'node_modules/knockout-mapping/dist/knockout.mapping.min.js',
        'node_modules/bootbox/dist/bootbox.min.js',
        'node_modules/metismenu/dist/metisMenu.min.js',
        'node_modules/jquery-slimscroll/jquery.slimscroll.min.js',
        'node_modules/waypoints/lib/jquery.waypoints.min.js',
        'node_modules/jquery.counterup/jquery.counterup.min.js',
        'node_modules/chartist/dist/chartist.min.js',
        'node_modules/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js',
        'node_modules/jquery-sparkline/jquery.sparkline.min.js',
        'node_modules/jquery-toast-plugin/dist/jquery.toast.min.js',
        'node_modules/jquery-validation/dist/jquery.validate.min.js',
        'node_modules/jquery-validation/dist/localization/messages_pt_BR.min.js',
        'dist/js/custom.min.js'
    ];

    if (!config.production) {
        files = files.map(e => e.split('.min.').join('.'));
    }

    return gulp.src(files)
            .pipe(gulpif(!config.production, sourcemaps.init()))
            .pipe(concat('app.js'))
            .pipe(addBOM())
            .pipe(gulpif(!config.production, sourcemaps.write('./')))
            .pipe(gulp.dest('dist/js'));
});

gulp.task('app-ko', function() {
    return gulp.src([
        'model/jobDetail.js',
        'ko/handlers.js',
        'ko/home.js',
        'ko/minha-conta.js'])
    .pipe(concat('app-ko.js'))
    .pipe(gulpif(!config.production, uglify()))
    .pipe(addBOM())
    .pipe(gulp.dest('dist/js/'));
});

gulp.task('default', gulp.series(
    'login-css',
    'app-css',
    'copy-fonts',
    'custom-js',
    'app-js',
    'app-ko'
));
