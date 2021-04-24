$(document).ready(function(){
    if ($('#home').length === 1) {
        'use strict';

        function HomeVM(jQ) {
            var self = this;

            self.nomePessoa = ko.observable('');
            self.ultimoLogin = ko.observable('');
            self.cidadeEstado = ko.observable('');
            self.totalMes = ko.observable(0);
            self.totalSemana = ko.observable(0);
            self.totalHoje = ko.observable(0);
            self.vagasSelecionadas = ko.observableArray([]);
            self.ultimasVagas = ko.observableArray([]);
            self.topVagas = ko.observableArray([]);
            self.abrirModal = ko.observable(false);
            self.vaga = ko.mapping.fromJS(JobDetail.getData());
            self.currentPageUser = ko.observable(1);
            self.currentPageLast = ko.observable(1);
            self.currentPageTop = ko.observable(1);
            self.cityId = ko.observable(1);
            self.lastJobPagination = ko.mapping.fromJS(HomePagination.getData());

            iniciar();

            function iniciar() {
                jQ.getJSON('/app/base/current-user', (response) => {
                    if (response.lastLogin) {
                        const dateTime = response.lastLogin.split(' ');
                        self.ultimoLogin(dateTime[0] + ' às ' + dateTime[1]);
                    }
                    self.nomePessoa(response.firstName || '');
                });

                jQ.getJSON('/app/home/all-jobs', (response) => {
                    if (response.cityName && response.stateName) {
                        self.cidadeEstado(`${response.cityName}/${response.stateName}`);
                    }
                    self.totalMes(response.monthJobs || 0);
                    self.totalSemana(response.weekJobs || 0);
                    self.totalHoje(response.todayJobs || 0);
                    self.vagasSelecionadas(response.userJobPagination.jobList || []);
                    self.ultimasVagas(response.lastJobPagination.jobList || []);
                    self.topVagas(response.topJobPagination.jobList || []);
                    self.cityId(response.cityId || 0);

                    ko.mapping.fromJS(response.lastJobPagination, self.lastJobPagination);
                });
            }

            self.verVaga = function(item) {
                ko.mapping.fromJS(item, self.vaga);
                self.abrirModal(true);
            };

            self.lastJobPaginationNext = function() {
                if (!self.lastJobPagination.hasNextPage()) {
                    return;
                }

                const currentPage = self.lastJobPagination.currentPage();
                let idx = currentPage - 1;
                idx++;
                idx += 1;

                const query = '?page=' + idx + '&city_id=' + self.cityId();
                jQ.getJSON('/app/home/last-jobs' + query, (response) => {
                    self.ultimasVagas([]);
                    ko.mapping.fromJS(response, self.lastJobPagination);
                    if (response.jobList.length > 0) {
                        self.ultimasVagas(response.jobList);
                    }
                });
            };

            self.lastJobPaginationFirst = function() {
                const query = '?page=1&city_id=' + self.cityId();
                jQ.getJSON('/app/home/last-jobs' + query, (response) => {
                    self.ultimasVagas([]);
                    ko.mapping.fromJS(response, self.lastJobPagination);
                    if (response.jobList.length > 0) {
                        self.ultimasVagas(response.jobList);
                    }
                });
            };

            self.lastJobPaginationPrev = function() {
                if (!self.lastJobPagination.hasPreviousPage()) {
                    return;
                }

                const currentPage = self.lastJobPagination.currentPage();
                let idx = currentPage - 1;
                idx--;
                idx += 1;

                const query = '?page=' + idx + '&city_id=' + self.cityId();
                jQ.getJSON('/app/home/last-jobs' + query, (response) => {
                    self.ultimasVagas([]);
                    ko.mapping.fromJS(response, self.lastJobPagination);
                    if (response.jobList.length > 0) {
                        self.ultimasVagas(response.jobList);
                    }
                });
            };

            self.lastJobPaginationLast = function() {
                const query = '?page=' + self.lastJobPagination.pages().length + '&city_id=' + self.cityId();
                jQ.getJSON('/app/home/last-jobs' + query, (response) => {
                    self.ultimasVagas([]);
                    ko.mapping.fromJS(response, self.lastJobPagination);
                    if (response.jobList.length > 0) {
                        self.ultimasVagas(response.jobList);
                    }
                });
            };

            self.lastJobPaginationPage = function(page) {
                const query = '?page=' + page + '&city_id=' + self.cityId();
                jQ.getJSON('/app/home/last-jobs' + query, (response) => {
                    self.ultimasVagas([]);
                    ko.mapping.fromJS(response, self.lastJobPagination);
                    if (response.jobList.length > 0) {
                        self.ultimasVagas(response.jobList);
                    }
                });
            };
        }

        ko.applyBindings(new HomeVM(jQuery));
    }
});