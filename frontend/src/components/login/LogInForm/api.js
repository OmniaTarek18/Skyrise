// onSubmit function to handle form submission
export const LoginAPI = async (values, actions) => {
  console.log(values);
  console.log(actions);
  const url = `http://localhost:8080/logIn?email=${values.email}&password=${values.password}`;
  try {
    const response = await fetch(url);
    if (!response.ok) {
      actions.setStatus("fail");
      throw new Error(`Response status: ${response.status}`);
    }
    const json = await response.json();
    actions.setStatus("success");
    console.log(json);
  } catch (error) {
    actions.setStatus("fail");
    console.error(error.message);
  }
};
