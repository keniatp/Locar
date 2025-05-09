function createFetchRequest(endpoint, requestBody) {
	return () =>
		fetch(`http://localhost:8080/${endpoint}`, {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(requestBody),
		});
}

function createEndpointConfig(endpoint, bodyBuilder, requiredArgs) {
	return () => ({
		request: createFetchRequest(endpoint, bodyBuilder()),
		requiredArgs,
	});
}

const apiMethods = {
	createUser: createEndpointConfig("usuario", () => ({
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
			console.log(response || "Empty response received");
			return;
		}

		Object.entries(response).forEach(([key, value]) => {
			console.log(`${key}: ${JSON.stringify(value)}`);
		});
	})
	.catch((error) => {
		console.error("Request failed:", error.message);
		Deno.exit(1);
	});
