import { ChangeEvent, useState } from "react";

export const usePassword = () => {
  //
  const [password, setPassword] = useState("");
  const [passwordInvalidLengthMessage, setPasswordInvalidLengthMessage] =
    useState("");

  const minLength: number = 8;
  const maxLength: number = 128;

  const handlePasswordChange = (e: ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const handlePasswordBlur = (e: any) => {
    //
    const value = e.target.value;
    if (value.length < minLength || maxLength < value.length) {
      setPasswordInvalidLengthMessage(
        `パスワードは${minLength}文字以上${maxLength}文字以下で入力してください。`
      );
    } else {
      setPasswordInvalidLengthMessage("");
    }
  };

  return {
    password,
    setPassword,
    maxLength,
    minLength,
    passwordInvalidLengthMessage,
    setPasswordInvalidLengthMessage,
    handlePasswordChange,
    handlePasswordBlur,
  };
};
