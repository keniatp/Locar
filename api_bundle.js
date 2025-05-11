function createPOSTRequest(endpoint, requestBody) {
	return () =>
		fetch(`http://localhost:8080/${endpoint}`, {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(requestBody),
		});
}

function createGETRequest(endpoint, requestBody) {
	return () =>
		fetch(
			`http://localhost:8080/${endpoint}?${
				Object.entries(requestBody || {}).map(([key, value]) =>
					`${key}=${value}`
				).join("&")
			}`,
		);
}

function createPOSTConfig(endpoint, bodyBuilder, requiredArgs) {
	return () => ({
		request: createPOSTRequest(endpoint, bodyBuilder()),
		requiredArgs,
	});
}

function createGETConfig(endpoint, bodyBuilder, requiredArgs = 0) {
	return () => ({
		request: createGETRequest(
			endpoint,
			bodyBuilder instanceof Function ? bodyBuilder() : undefined,
		),
		requiredArgs,
	});
}

const apiMethods = {
	createUser: createPOSTConfig("usuario", () => ({
		cadastroUsuario: {
			id_cadastroUsuario: 0,
			nome: Deno.args[1],
			cpf: Deno.args[2],
			telefone: Deno.args[3],
			email: Deno.args[4],
			dataDeNascimento: Deno.args[5],
			comprovanteCNH_id_comprovanteCNH: 0,
		},
		comprovanteCNH: {
			id_comprovanteCNH: 0,
			numeroIdentificacao: Deno.args[6],
			dataValidade: Deno.args[7],
			statusCNH: Deno.args[8],
			ufEmissao: Deno.args[9],
			categoria: Deno.args[10],
		},
	}), 10),
	getUsers: createGETConfig("usuarios"),
	createVehicle: createPOSTConfig("veiculo", () => ({
		documentacao: {
			id_documentacao: 0,
			ipva: Deno.args[1],
			seguroVencimento: Deno.args[2],
			quilometragemAtual: Deno.args[3],
			dataAquisicao: Deno.args[4],
			vistoria: Deno.args[5],
		},
		cadastroVeiculo: {
			id_cadastroVeiculo: 0,
			marca: Deno.args[6],
			modelo: Deno.args[7],
			ano: Deno.args[8],
			cor: Deno.args[9],
			placa: Deno.args[10],
			valorTotal: Deno.args[11],
			statusOcupacao: Deno.args[12],
			renavam: Deno.args[13],
			tipoCambio: Deno.args[14],
			capacidade: Deno.args[15],
			documentacao_id_documentacao: 0,
		},
		manutencaoVeiculo: {
			id_manutencaoVeiculo: 0,
			dataManutencao: Deno.args[16],
			tipoManutencao: Deno.args[17],
			custo: Deno.args[18],
			cadastroVeiculo_id_cadastroVeiculo: 0,
		},
	}), 18),
	getVehicles: createGETConfig("veiculos"),
	createEmployee: createPOSTConfig("funcionario", () => ({
		id_cadastroFuncionario: 0,
		nome: Deno.args[1],
		cpf: Deno.args[2],
		email: Deno.args[3],
		telefone: Deno.args[4],
		cargo: Deno.args[5],
		senha: Deno.args[6],
	}), 6),
	getEmployee: createGETConfig("funcionario", () => ({
		email: Deno.args[1],
		senha: Deno.args[2],
	}), 2),
	createRenting: createPOSTConfig("locacao", () => ({
		locacaoDados: {
			id_locacaoDados: 0,
			valorTotal: Deno.args[1],
			status: Deno.args[2],
			dataSaida: Deno.args[3],
			dataDevolucao: Deno.args[4],
			cadastroUsuario_id_cadastroUsuario: Deno.args[5],
			cadastroFuncionario_id_cadastroFuncionario: Deno.args[6],
			cadastroVeiculo_id_cadastroVeiculo: Deno.args[7],
		},
		devolucao: {
			id_devolucao: 0,
			dataDevolucao: Deno.args[8],
			combustivelRestante: Deno.args[9],
			statusVeiculo: Deno.args[10],
			observacao: Deno.args[11],
		},
		pagamento: {
			id_pagamento: 0,
			valorTotal: Deno.args[12],
			data: Deno.args[13],
			metodoPagamento: Deno.args[14],
			devolucao_id_devolucao: 0,
			taxa_id_taxa: Deno.args[15],
			locacaoDados_id_locacaoDados: 0,
		},
	}), 15),
	getFees: createGETConfig("taxas"),
};

const [command] = Deno.args;

if (!command || !apiMethods[command]) {
	const availableMethods = Object.keys(apiMethods).join(", ");
	console.error(`Invalid method! Available methods: ${availableMethods}`);
	Deno.exit(1);
}

const getMethodConfig = apiMethods[command];
const methodConfig = getMethodConfig();
const receivedArgs = Deno.args.length - 1;

if (receivedArgs !== methodConfig.requiredArgs) {
	console.error(
		`Method ${command} requires ${methodConfig.requiredArgs} arguments, ` +
			`received ${receivedArgs}`,
	);
	Deno.exit(1);
}

methodConfig.request()
	.then((response) =>
		response.headers.get("Content-Type")?.includes("json")
			? response.json()
			: response.text()
	)
	.then((response) => {
		if (typeof response === "string") {
			response
				? console.log(response)
				: console.error("Empty response received");
			return;
		}

		for (
			const element of response instanceof Array ? response : [response]
		) {
			Object.entries(element).forEach(([key, value]) => {
				console.log(`${key}: ${JSON.stringify(value)}`);
			});
			console.log("_\t_");
		}
	})
	.catch((error) => {
		console.error("Request failed:", error.message);
		Deno.exit(1);
	});
