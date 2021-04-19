class App {
    static async getJson(pUrl) {
        try {
            const response = await fetch(pUrl, {
                method: 'GET',
                credentials: 'include',
                headers: new Headers({'Content-Type': 'application/json'}),
                cache: 'default',
            });

            if (!response.ok) {
                throw new Error(`${response.status} - ${response.statusText}`);
            }

            const json = await response.json();
            return json;
        } catch (e) {
            if (e.toString().includes('Failed to fetch')) {
                throw new Error('Erro de comunicação com o servidor!');
            } else {
                throw new Error(e.toString());
            }
        }
    }

    static async postJson(pUrl, pBodyJson) {
        try {
            const response = await fetch(pUrl, {
                method: 'POST',
                headers: new Headers({'Content-Type': 'application/json'}),
                cache: 'default',
                body: JSON.stringify(pBodyJson),
            });

            if (!response.ok) {
                throw new Error(`${response.status} - ${response.statusText}`);
            }

            const json = await response.json();
            return json;
        } catch (e) {
            if (e.toString().includes('Failed to fetch')) {
                throw new Error('Erro de comunicação com o servidor!');
            } else {
                throw new Error(e.toString());
            }
        }
    }

    static async login(pUrl, pBodyJson) {
        try {
            const response = await fetch(pUrl, {
                method: 'POST',
                credentials: 'include',
                headers: new Headers({'Content-Type': 'application/json'}),
                cache: 'default',
                body: JSON.stringify(pBodyJson),
            });

            if (!response.ok) {
                throw new Error(`${response.status} - ${response.statusText}`);
            }

            const json = await response.json();
            return json;
        } catch (e) {
            console.error(e);
            if (e.toString().includes('Failed to fetch')) {
                throw new Error('Erro de comunicação com o servidor!');
            } else if (e.toString().includes('NetworkError when attempting')) {
                throw new Error('Sem autorização!');
            } else {
                throw new Error(e.toString());
            }
        }
    }
}
