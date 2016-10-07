module.exports = function(grunt) {

	// loading all neccessary grunt tasks
	require('load-grunt-tasks')(grunt);

	var paths = {
			src: {
				jsAppModules: 'src/main/webapp/app/app.module.js', // needs to be loaded first!
				jsApp: 'src/main/webapp/app/**/*.js',
				jsTestApp: 'src/main/webapp/test/**/*.test.js',
				fontsBootsrap: 'bower_components/bootstrap/fonts/*',
				less: 'src/main/webapp/assets/less/archkb.less',
				dist: 'src/main/webapp/dist/**/*',
				views: 'src/main/webapp/app/**/*View.html',
				images: 'src/main/webapp/assets/images/',
				translations: 'src/main/webapp/assets/translations/**/*',
				indexHtml: 'src/main/webapp/index.html',
				favicon: 'src/main/webapp/favicon.ico'
			},
			dest: {
				buildDev: 'src/main/webapp/build/dev/',
				buildDist: 'src/main/webapp/build/dist/',
				jsApp: 'src/main/webapp/build/dev/scripts/app.js',
				jsMinApp: 'src/main/webapp/build/dist/scripts/app.min.js',
				jsTestApp: 'src/main/webapp/build/dev/scripts/app.test.js',
				jsVendor: 'src/main/webapp/build/dev/scripts/vendor.js',
				jsMinVendor: 'src/main/webapp/build/dist/scripts/vendor.min.js',
				jsTestVendor: 'src/main/webapp/build/dev/scripts/vendor.test.js',
				fontsDev: 'src/main/webapp/build/dev/fonts/',
				fontsDist: 'src/main/webapp/build/dist/fonts/',
				cssDev: 'src/main/webapp/build/dev/styles/app.css',
				cssDist: 'src/main/webapp/build/dist/styles/app.min.css',

				viewsDev: 'src/main/webapp/build/dev/views/',
				viewsDist: 'src/main/webapp/build/dist/views/',

				jsViewsDev: 'src/main/webapp/build/dev/scripts/views.js',
				jsViewsDist: 'src/main/webapp/build/dist/scripts/views.min.js',

				imagesDev: 'src/main/webapp/build/dev/images/',
				imagesDist: 'src/main/webapp/build/dist/images/',
				translationsDev: 'src/main/webapp/build/dev/translations/',
				translationsDist: 'src/main/webapp/build/dist/translations/',
			}
	};

	grunt
	.initConfig({
		paths : paths,
		pkg: grunt.file.readJSON('package.json'),

		// Concat the Bower Dependencies -> see: https://www.npmjs.com/package/grunt-bower-concat
		bower_concat: {
			vendorJs: {
				options: {
					seperator: ';'
				},
				dest: {
					'js':  '<%= paths.dest.jsVendor %>'
				}
			},

			vendorTestJs: {
				options: {
					seperator: ';'
				},
				includeDev: true,
				include: ['angular-mocks'],
				dest: {
					'js': '<%= paths.dest.jsTestVendor %>'
				}
			}
		},

		// Concat the AckKB JS Files here
		concat: {
			appJs: {
				options: {
					stripBanners: true,
					banner: '/*! <%= pkg.name %> - v<%= pkg.version %> - ' +
			        '<%= grunt.template.today("dd-mm-yyyy") %> */',
			        seperator: ';'
				},
				src: ['<%= paths.src.jsAppModules %>', '<%= paths.src.jsApp %>'], // First concat the modules js, than the rest
				dest: '<%= paths.dest.jsApp %>'
			},
			appTestJs: {
				src: '<%= paths.src.jsTestApp %>',
				dest: '<%= paths.dest.jsTestApp %>'
			}
		},

		uglify: {
			options: {
				compress: true,
				mangle: true,
				soruceMap: true
			},

			appJs: { // Uglify directly from app source
				src: '<%= paths.src.jsApp %>',
				dest: '<%= paths.dest.jsMinApp %>'
			},
			vendorJs: { // use the concated Vendor JS and then uglify it
				src: '<%= paths.dest.jsVendor %>',
				dest: '<%= paths.dest.jsMinVendor %>'
			}
		},

		// Copy necessary files into specific folders
		copy : {
			dev: {
				files: [
				    // copying bootstrap fonts to dev
			        {expand: true, src: '<%= paths.src.fontsBootsrap %>', dest: '<%= paths.dest.fontsDev %>', flatten: true},
			        // copying views to dev
			        {expand: true, src: '<%= paths.src.views %>', dest: '<%= paths.dest.viewsDev %>', flatten: true},
			        // copying images to dev
			        {expand: true, src: '**', dest: '<%= paths.dest.imagesDev %>', cwd: '<%= paths.src.images %>'},
			        // copying translations to dev
			        {expand: true, src: '<%= paths.src.translations %>', dest: '<%= paths.dest.translationsDev %>', flatten: true},
			        // copying index.html and favicon to dev
			        {expand: true, src: ['<%= paths.src.indexHtml %>', '<%= paths.src.favicon %>'], dest: '<%= paths.dest.buildDev %>', flatten: true}
			    ]
			},

			dist: {
				files: [
				        //copying bootstrap fonts to dist
				        {expand: true, src: ['<%= paths.src.fontsBootsrap %>'], dest: '<%= paths.dest.fontsDist %>', flatten: true},
				        // copying views to dist
				        {expand: true, src: ['<%= paths.src.views %>'], dest: '<%= paths.dest.viewsDist %>', flatten: true},
				        // copying images to dist
				        {expand: true, src: '**', dest: '<%= paths.dest.imagesDist %>', cwd: '<%= paths.src.images %>'},
				        // copying translations to dist
				        {expand: true, src: ['<%= paths.src.translations %>'], dest: '<%= paths.dest.translationsDist %>', flatten: true},
				        // copying index.html to dist
				        {expand: true, src: ['<%= paths.src.indexHtml %>', '<%= paths.src.favicon %>'], dest: '<%= paths.dest.buildDist %>', flatten: true}
				    ]
			}
		},

		// Combining all html Views to Javascript (no ajax requests needed)
		ngtemplates: {

			options: {
				module: 'archkb',
				url: function(url) {
					// Cannot use the absolute url -> because views are being copied to views folder! (see copy)
					return 'views' + url.substring(url.lastIndexOf('/'));
				}
			},

			dev: {
				src: '<%= paths.src.views %>',
				dest: '<%= paths.dest.jsViewsDev %>'
			},
			dist: {
				src: '<%= paths.src.views %>',
				dest: '<%= paths.dest.jsViewsDist %>',
				options: {
					htmlmin: {
						collapseBooleanAttributes:      true,
						collapseWhitespace:             true,
						removeAttributeQuotes:          true,
						removeComments:                 true, // Only if you don't use comment directives!
						removeEmptyAttributes:          true,
						removeRedundantAttributes:      true,
						removeScriptTypeAttributes:     true,
						removeStyleLinkTypeAttributes:  true
					}
				}
			},
		},

		// Compile Less into css (custom styles)
		less : {
			options : {
				sourceMap : false
			},

			uncompressed : {
				options : {
					compress : false
				},

				files : {
					'<%= paths.dest.cssDev %>' : '<%= paths.src.less %>'
				}
			},

			compressed : {

				options : {
					compress: true
				},

				files : {
					'<%= paths.dest.cssDist %>' : '<%= paths.src.less %>'
				}
			}
		},

		// validating js files with jshint
		jshint: {

			options: {
                reporter: 'node_modules/jshint-stylish',
				ignores: ['<%= paths.src.dist %>']
			},

			scripts : ['Gruntfile.js', '<%= paths.src.jsApp %>'],
		},

		// karma test tasks
		karma: {
			unit: {
				options: {
					browsers: ['PhantomJS'],
					singleRun: true,
					frameworks: ['jasmine'],
					files: ['<%= paths.dest.jsVendor %>', '<%= paths.dest.jsTestVendor %>', '<%= paths.dest.jsApp %>', '<%= paths.dest.jsTestApp %>']
				}
			}
		}

	});

	// Default task
	grunt.registerTask('default', ['bower_concat:vendorJs', 'concat:appJs', 'copy:dev', 'ngtemplates:dev', 'less:uncompressed', 'jshint']);
	// Distribute -> with minification
	grunt.registerTask('dist', ['bower_concat:vendorJs', 'concat:appJs', 'copy:dist', 'ngtemplates:dist', 'less:compressed', 'uglify', 'jshint']);
	// Test task -> compile test resources and start test1
	grunt.registerTask('test', ['bower_concat', 'concat', 'copy:dev', 'ngtemplates:dev' ,'less:uncompressed', 'jshint', 'karma']);
};